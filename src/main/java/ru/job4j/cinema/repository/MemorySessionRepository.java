package ru.job4j.cinema.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.cinema.model.FilmSession;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Repository
public class MemorySessionRepository implements SessionRepository {
    private Map<Integer, FilmSession> sessions = new HashMap<>();

    public MemorySessionRepository() {
        sessions.put(0, new FilmSession(0, 0, 0,
                LocalDateTime.of(2024, 5, 14, 10, 0),
                LocalDateTime.of(2024, 5, 14, 12, 0),
                666
                ));
        sessions.put(1, new FilmSession(1, 1, 1,
                LocalDateTime.of(2024, 5, 14, 14, 0),
                LocalDateTime.of(2024, 5, 14, 16, 0),
                700
        ));
        sessions.put(2, new FilmSession(2, 2, 2,
                LocalDateTime.of(2024, 5, 15, 20, 0),
                LocalDateTime.of(2024, 5, 15, 22, 0),
                700
        ));
    }

    @Override
    public Collection<FilmSession> findAll() {
        return sessions.values();
    }
}
