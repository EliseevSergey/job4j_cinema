package ru.job4j.cinema.repository;

import ru.job4j.cinema.dto.FilmPreview;
import ru.job4j.cinema.model.Film;

import java.util.Collection;

public interface FilmPreviewRepository {
    FilmPreview findById(int id);
}
