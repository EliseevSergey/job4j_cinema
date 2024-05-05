package ru.job4j.cinema.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.cinema.model.Film;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Repository
public class MemoryFilmRepository implements FilmRepository {
    private static final MemoryFilmRepository INSTANCE = new MemoryFilmRepository();
    private int nextId = 1;
    private final Map<Integer, Film> films = new HashMap<>();

    public MemoryFilmRepository() {
        save(new Film(0, "Godzilla", "description", 2000, 2, 16, 120, 0));
        save(new Film(0, "Rambo", "description", 1999, 3, 21, 110, 1));
    }

    public MemoryFilmRepository getInstance() {
        return INSTANCE;
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

    @Override
    public Collection<Film> findAll() {
        return films.values();
    }
}
