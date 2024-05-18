package ru.job4j.cinema.service;

import ru.job4j.cinema.dto.FilmDto;
import ru.job4j.cinema.model.Film;

import java.util.Collection;
import java.util.Optional;

public interface FilmService {
    Film save(Film film);

    boolean deleteById(int id);

    boolean update(Film film);

    Optional<Film> findById(int id);

    Collection<Film> findAll();

    Collection<FilmDto> findAllFilmDto();
}
