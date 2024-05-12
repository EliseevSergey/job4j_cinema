package ru.job4j.cinema.service;

import org.springframework.stereotype.Service;
import ru.job4j.cinema.dto.FilmPreview;
import ru.job4j.cinema.model.Film;
import ru.job4j.cinema.repository.FilmRepository;
import ru.job4j.cinema.repository.GenreRepository;
import ru.job4j.cinema.repository.MemoryFilmRepository;
import ru.job4j.cinema.repository.MemoryGenreRepository;
import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SimpleFilmService implements FilmService {
    private final FilmRepository filmRepository = new MemoryFilmRepository();
    private final GenreRepository genreRepository = new MemoryGenreRepository();

    public Collection<Film> findAll() {
        return filmRepository.findAll();
    }

    @Override
    public Film save(Film film) {
        return null;
    }

    @Override
    public boolean deleteById(int id) {
        return false;
    }

    @Override
    public boolean update(Film film) {
        return false;
    }

    @Override
    public Optional<Film> findById(int id) {
        return Optional.empty();
    }

    public Collection<FilmPreview> findAllFilmPreview() {
        return filmRepository.findAll().stream().map((film) ->  new FilmPreview(film.getId(), film.getName(),
                film.getDescription(), film.getYear(), film.getMinAge(), film.getDuration(),
                genreRepository.findById(film.getGenreId()).getName(), film.getFileId())).collect(Collectors.toList());
    }
}
