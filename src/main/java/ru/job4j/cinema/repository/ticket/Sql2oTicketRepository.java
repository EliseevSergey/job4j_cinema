package ru.job4j.cinema.repository.ticket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.sql2o.Connection;
import org.sql2o.Query;
import org.sql2o.Sql2o;
import ru.job4j.cinema.model.Ticket;

import java.sql.SQLException;
import java.util.Optional;

import ru.job4j.cinema.repository.user.Sql2oUserRepository;

@Repository
public class Sql2oTicketRepository implements TicketRepository {
    private final Sql2o sql2o;

    static final Logger LOGGER =
            LoggerFactory.getLogger(Sql2oUserRepository.class);

    public Sql2oTicketRepository(Sql2o sql2o) {
        this.sql2o = sql2o;
    }

    @Override
    public Optional<Ticket> save(Ticket ticket) {
        try (Connection connection = sql2o.open()) {
            var sql = """
                    INSERT INTO tickets (session_id, row_number, place_number, user_id)
                    VALUES (:sessionId, :row, :place, :userId)
                    """;
            Query query = connection.createQuery(sql, true)
                    .addParameter("sessionId", ticket.getSessionId())
                    .addParameter("row", ticket.getRow())
                    .addParameter("place", ticket.getPlace())
                    .addParameter("userId", ticket.getUserId());
            int generatedId = query.executeUpdate().getKey(Integer.class);
            ticket.setId(generatedId);
            return Optional.of(ticket);
        } catch (Exception e) {
            if (isPostgresUniqueConstraintViolation(e)) {
                return Optional.empty();
            } else {
                throw new RuntimeException("Failed to save ticket: " + e.getMessage(), e);
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
    public Optional<Ticket> findById(int id) {
        try (Connection connection = sql2o.open()) {
            var sql = """
                    SELECT * FROM tickets WHERE id = :id
                    """;
            Query query = connection.createQuery(sql)
                    .addParameter("id", id);
            Optional<Ticket> ticket = Optional.ofNullable(query.setColumnMappings(Ticket.COLUMN_MAPPING)
                    .executeAndFetchFirst(Ticket.class));
            return ticket;
        }
    }
}
