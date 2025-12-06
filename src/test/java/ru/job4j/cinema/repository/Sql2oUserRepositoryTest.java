package ru.job4j.cinema.repository;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.job4j.cinema.configuration.DatasourceConfiguration;
import ru.job4j.cinema.model.User;
import ru.job4j.cinema.repository.user.Sql2oUserRepository;

import java.util.Optional;
import java.util.Properties;

import static org.assertj.core.api.Assertions.*;

public class Sql2oUserRepositoryTest {
    private static Sql2oUserRepository sql2oUserRepository;

    @BeforeAll
    public static void initRepository() throws Exception {
        var properties = new Properties();
        try (var inputStream = Sql2oFilmRepositoryTest.class
                .getClassLoader().getResourceAsStream("connection.properties")) {
            properties.load(inputStream);
        }
        var url = properties.getProperty("datasource.url");
        var username = properties.getProperty("datasource.username");
        var password = properties.getProperty("datasource.password");

        var configuration = new DatasourceConfiguration();
        var datasource = configuration.connectionPool(url, username, password);
        var sql2o = configuration.databaseClient(datasource);

        sql2oUserRepository = new Sql2oUserRepository(sql2o);
    }

    @Test
    public void whenSaveUserThanGetSame() {
        User user = new User(1, "mail@mail.ru", "user", "123");
        sql2oUserRepository.save(user);
        User actualUser = sql2oUserRepository.findByEmailAndPassword("mail@mail.ru", "123").get();
        assertThat(actualUser).usingRecursiveComparison().isEqualTo(user);
    }

    @Test
    public void whenSaveTheSameThenThrow() {
        User user = new User(1, "mail@mail.ru", "user", "123");
        User userWithSameMail = new User(1, "mail@mail.ru", "user", "123");
        assertThat(sql2oUserRepository.save(userWithSameMail)).isEqualTo(Optional.empty());
    }
}
