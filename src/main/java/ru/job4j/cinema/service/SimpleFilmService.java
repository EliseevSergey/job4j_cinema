package ru.job4j.cinema.service;

import org.springframework.stereotype.Service;
import ru.job4j.cinema.dto.FilmDto;
import ru.job4j.cinema.model.Film;
import ru.job4j.cinema.repository.*;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SimpleFilmService implements FilmService {
    /*private final FilmRepository filmRepository = new MemoryFilmRepository();
    private final GenreRepository genreRepository = new MemoryGenreRepository();*/

    private final FilmRepository filmRepository;
    private final GenreRepository genreRepository;

    public SimpleFilmService(FilmRepository Sql2oFilmRepository, GenreRepository Sql2oGenreRepository) {
        this.filmRepository = Sql2oFilmRepository;
        this.genreRepository = Sql2oGenreRepository;
    }

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
        return filmRepository.findById(id);
    }

    public FilmDto findByIdDto(int id) {
        Film film = filmRepository.findById(id).get();
        return new FilmDto(film.getId(), film.getName(),
                film.getDescription(), film.getYear(), film.getMinAge(), film.getDuration(),
                genreRepository.findById(film.getGenreId()).getName(), film.getFileId());
    }

    public Collection<FilmDto> findAllFilmDto() {
        return filmRepository.findAll().stream().map((f) ->  new FilmDto(f.getId(), f.getName(),
                f.getDescription(), f.getYear(), f.getMinAge(), f.getDuration(),
                genreRepository.findById(f.getGenreId()).getName(), f.getFileId())).collect(Collectors.toList());
    }
}
