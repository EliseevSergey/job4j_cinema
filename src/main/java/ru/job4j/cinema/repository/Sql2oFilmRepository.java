package ru.job4j.cinema.repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.sql2o.Connection;
import org.sql2o.Query;
import org.sql2o.Sql2o;
import ru.job4j.cinema.model.Film;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class Sql2oFilmRepository implements FilmRepository {
    private final Sql2o sql2o;

    public Sql2oFilmRepository(Sql2o sql2o) {
        this.sql2o = sql2o;
    }

    @Override
    public Film save(Film film) {
        return null;
    }

    @Override
    public boolean deleteById(int id) {
        try (Connection connection = sql2o.open()) {
            var sql = """
                    DELETE FROM films where id = :id
                    """;
            connection.createQuery(sql).addParameter("id", id).executeUpdate();
            return connection.getResult() != 0;
        }
    }

    @Override
    public boolean update(Film film) {
        return false;
    }

    @Override
    public Optional<Film> findById(int id) {
        try (Connection connection = sql2o.open()) {
            var sql = """
                    SELECT * FROM  films WHERE id =  :id
                    """;
            Query query = connection.createQuery(sql)
                    .addParameter("id", id);
            Optional<Film> film = Optional.ofNullable(query.setColumnMappings(Film.COLUMN_MAPPING)
                    .executeAndFetchFirst(Film.class));
            return film;
        }
    }

    @Override
    public Collection<Film> findAll() {
        try (Connection connection = sql2o.open()) {
            var sql = "SELECT * FROM films";
            Query query = connection.createQuery(sql);
            List<Film> films = query.setColumnMappings(Film.COLUMN_MAPPING)
                    .executeAndFetch(Film.class);
            return films;
        }
    }
}
