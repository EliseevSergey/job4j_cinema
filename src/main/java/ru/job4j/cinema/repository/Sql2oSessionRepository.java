package ru.job4j.cinema.repository;

import org.springframework.stereotype.Repository;
import org.sql2o.Connection;
import org.sql2o.Query;
import org.sql2o.Sql2o;
import ru.job4j.cinema.model.FilmSession;
import java.util.Collection;

@Repository
public class Sql2oSessionRepository implements SessionRepository {
    private final Sql2o sql2o;

    public Sql2oSessionRepository(Sql2o sql2o) {
        this.sql2o = sql2o;
    }

    @Override
    public Collection<FilmSession> findAll() {
        try (Connection connection = sql2o.open()) {
            String sql = "SELECT * FROM film_sessions";
            return connection.createQuery(sql)
                    .setColumnMappings(FilmSession.COLUMN_MAPPING)
                    .executeAndFetch(FilmSession.class);
        }
    }

    @Override
    public FilmSession findById(int id) {
        try (Connection connection = sql2o.open()) {
            String sql = "SELECT * FROM film_sessions WHERE id =:id";
            Query query = connection.createQuery(sql)
                    .addParameter("id", id)
                    .setColumnMappings(FilmSession.COLUMN_MAPPING);
            return query.executeAndFetchFirst(FilmSession.class);
        }
    }
}
