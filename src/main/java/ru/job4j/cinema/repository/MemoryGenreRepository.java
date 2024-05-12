package ru.job4j.cinema.repository;

import ru.job4j.cinema.model.Genre;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class MemoryGenreRepository implements GenreRepository{
    private final Map<Integer, Genre> genres = new HashMap<>();

    public MemoryGenreRepository(){
        genres.put(0, new Genre(0, "Фентази"));
        genres.put(1, new Genre(1, "Боевик"));
        genres.put(2, new Genre(2, "Фантастика"));
    }

    @Override
    public Optional<Genre> findById(int id) {
        return Optional.ofNullable(genres.get(id));
    }
}
