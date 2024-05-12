package ru.job4j.cinema.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.job4j.cinema.model.Film;
import ru.job4j.cinema.service.SimpleFilmService;
import ru.job4j.cinema.service.SimpleGenreService;

import java.util.Collection;

@Controller
@RequestMapping("/films")
public class FilmController {
    private final SimpleFilmService filmService = new SimpleFilmService();
    private final SimpleGenreService genreService = new SimpleGenreService();

    @GetMapping
    public String getAll(Model model) {
        model.addAttribute("previews", filmService.findAllFilmPreview());
        return "films/list";
    }
}
