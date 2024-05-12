package ru.job4j.cinema.service;

import ru.job4j.cinema.model.Genre;
import ru.job4j.cinema.repository.GenreRepository;
import ru.job4j.cinema.repository.MemoryGenreRepository;

import java.util.Optional;

public class SimpleGenreService implements GenreService{
    private final GenreRepository genreRepository = new MemoryGenreRepository();

   /* public SimpleGenreService(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }*/

    /*@Override
    public Optional<Genre> findById(int id) {
        return genreRepository.findById(id);
    }*/

    @Override
    public Genre findById(int id) {
        return genreRepository.findById(id);
    }
}
