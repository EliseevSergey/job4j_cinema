package ru.job4j.cinema.service;

import ru.job4j.cinema.dto.SessionDto;
import ru.job4j.cinema.model.FilmSession;

import java.util.Collection;

public interface SessionService {
    Collection<FilmSession> findAll();

    //FilmSession findById(int id);

    SessionDto findByIdSessionDto(int id);
}
