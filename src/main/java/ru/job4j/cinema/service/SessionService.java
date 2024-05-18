package ru.job4j.cinema.service;

import ru.job4j.cinema.model.FilmSession;

import java.util.Collection;

public interface SessionService {
    Collection<FilmSession> findAll();
}
