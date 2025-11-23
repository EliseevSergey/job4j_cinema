package ru.job4j.cinema.repository;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.sql2o.Connection;
import ru.job4j.cinema.configuration.DatasourceConfiguration;
import ru.job4j.cinema.model.Film;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Properties;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Sql2oFilmRepositoryTest {
    private static Sql2oFilmRepository sql2oFilmRepository;
    private static Optional<Film> expectedFilm0 = Optional.of(new Film(0, "Godzilla", "description", 2000, 0, 16, 120, 0));
    private static Optional<Film> expectedFilm1 = Optional.of(new Film(1, "Rambo", "description", 1999, 1, 21, 110, 1));
    private static Optional<Film> expectedFilm2 = Optional.of(new Film(2, "Matrix", "description", 2008, 2, 16, 130, 2));


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

        sql2oFilmRepository = new Sql2oFilmRepository(sql2o);
    }

    @Test
    public void whenFindByIdThenGetFilm() {
        Optional<Film> expectedFilm2 = Optional.of(new Film(2, "Matrix", "description", 2008, 2, 16, 130, 2));
        assertThat(sql2oFilmRepository.findById(0)).isEqualTo(expectedFilm0);
        assertThat(sql2oFilmRepository.findById(1)).isEqualTo(expectedFilm1);
        assertThat(sql2oFilmRepository.findById(2)).isEqualTo(expectedFilm2);
    }

    @Test
    public void whenFindAllThenGetAllFilms() {
        List<Film> expectedList = List.of(expectedFilm0.get(), expectedFilm1.get(), expectedFilm2.get());
        assertThat(sql2oFilmRepository.findAll()).isEqualTo(expectedList);
    }
}