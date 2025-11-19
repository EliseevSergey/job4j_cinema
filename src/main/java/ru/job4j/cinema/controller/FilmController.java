package ru.job4j.cinema.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.job4j.cinema.service.SimpleFilmService;

@Controller
@RequestMapping("/films")
public class FilmController {
    private final SimpleFilmService filmService;

    public FilmController(SimpleFilmService filmService) {
        this.filmService = filmService;
    }

    @GetMapping
    public String getAll(Model model) {
        model.addAttribute("previews", filmService.findAllFilmDto());
        return "films/list";
    }
}
