package ru.job4j.cinema.repository;

import ru.job4j.cinema.model.FilmSession;

import java.util.Collection;

public interface SessionRepository {
    Collection<FilmSession> findAll();

    FilmSession findById(int id);
}
