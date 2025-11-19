package ru.job4j.cinema.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.cinema.model.Genre;

import java.util.Optional;

public interface GenreRepository {
    Genre findById(int id);
}
