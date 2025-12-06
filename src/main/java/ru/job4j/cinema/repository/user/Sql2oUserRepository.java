package ru.job4j.cinema.repository.user;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.sql2o.Connection;
import org.sql2o.Query;
import org.sql2o.Sql2o;
import ru.job4j.cinema.model.User;
import java.sql.SQLException;
import java.util.Optional;

@Repository
public class Sql2oUserRepository implements UserRepository {
    private final Sql2o sql2o;

    static final Logger LOGGER =
            LoggerFactory.getLogger(Sql2oUserRepository.class);

    public Sql2oUserRepository(Sql2o sql2o) {
        this.sql2o = sql2o;
    }

    @Override
    public Optional<User> save(User user) {
        try (Connection connection = sql2o.open()) {
            var sql = """
                    INSERT INTO users (email, name, password)
                    VALUES (:email, :name, :password)
                    """;
            Query query = connection.createQuery(sql, true)
                    .addParameter("email", user.getEmail())
                    .addParameter("name", user.getName())
                    .addParameter("password", user.getPassword());
            int generatedId = query.executeUpdate().getKey(Integer.class);
            user.setId(generatedId);
            return Optional.of(user);
        } catch (Exception e) {
            if (isPostgresUniqueConstraintViolation(e)) {
                return Optional.empty();
            } else {
                throw new RuntimeException("Failed to save user: " + e.getMessage(), e);
            }
        }
    }

    private boolean isPostgresUniqueConstraintViolation(Exception e) {
        Throwable cause = e;
        while (cause != null) {
            if (cause instanceof SQLException) {
                SQLException sqlEx = (SQLException) cause;
                if ("23505".equals(sqlEx.getSQLState())) {
                    return true;
                }
            }
            cause = cause.getCause();
        }
        return false;
    }

    @Override
    public Optional<User> findByEmailAndPassword(String email, String password) {
        try (Connection connection = sql2o.open()) {
            String sql = """
                    SELECT * FROM users WHERE email =:email AND password=:password
                    """;
            Query query = connection.createQuery(sql)
                    .addParameter("email", email)
                    .addParameter("password", password);
            User user = query.executeAndFetchFirst(User.class);
            return Optional.ofNullable(user);
        }
    }

    public void truncateAllUsers() {
        try (Connection connection = sql2o.open()) {
            String sql = "TRUNCATE TABLE users RESTART IDENTITY;";
            Query query = connection.createQuery(sql);
            query.executeUpdate();
        }
    }

    @ExceptionHandler(RuntimeException.class)
    public String exceptionHandler(RuntimeException ex, Model model) {
        model.addAttribute("message", "Что-то пошло не так:" + ex.getMessage());
        return "errors/404";
    }
}

