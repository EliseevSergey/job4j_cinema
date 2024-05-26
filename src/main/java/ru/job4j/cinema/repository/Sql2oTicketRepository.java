package ru.job4j.cinema.repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.sql2o.Connection;
import org.sql2o.Query;
import org.sql2o.Sql2o;
import ru.job4j.cinema.model.Ticket;
import java.util.Optional;
import org.sql2o.Sql2o;

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
                    VALUES (:id, :sessionId, :row, :place, :userId)
                    """;
            Query query = connection.createQuery(sql, true)
                    .addParameter("session_id", ticket.getSessionId())
                    .addParameter("row_number", ticket.getRow())
                    .addParameter("place_number", ticket.getPlace())
                    .addParameter("user_id", ticket.getUserId());
            int generatedId = query.executeUpdate().getKey(Integer.class);
            ticket.setId(generatedId);
            return Optional.of(ticket);
        } catch (Exception e) {
            LOGGER.error("Error while ticket save in DB", e);
        }
        return Optional.empty();
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
