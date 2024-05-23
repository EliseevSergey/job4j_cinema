package ru.job4j.cinema.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.job4j.cinema.service.SimpleFilmService;
import ru.job4j.cinema.service.TicketService;

@Controller
@RequestMapping("/tickets")
public class TicketController {
    private final TicketService tickets;
    private final SimpleFilmService filmService = new SimpleFilmService();

    public TicketController(TicketService tickets) {
        this.tickets = tickets;
    }




}
