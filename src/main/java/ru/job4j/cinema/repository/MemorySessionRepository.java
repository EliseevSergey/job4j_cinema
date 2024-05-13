package ru.job4j.cinema.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.cinema.model.FilmSession;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Repository
public class MemorySessionRepository implements SessionRepository{
    private Map<Integer, FilmSession> sessions = new HashMap<>();

    @Override
    public Collection<FilmSession> findAll() {
        return sessions.values();
    }
}
