package ru.job4j.cinema.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.job4j.cinema.dto.FilmDto;
import ru.job4j.cinema.dto.SessionDto;
import ru.job4j.cinema.model.Ticket;
import ru.job4j.cinema.service.SimpleFilmService;
import ru.job4j.cinema.service.SimpleSessionService;
import ru.job4j.cinema.service.SimpleTicketService;
import ru.job4j.cinema.service.TicketService;

@Controller
@RequestMapping("/tickets")
public class TicketController {
    private final TicketService tickets;
    private final SimpleFilmService filmService;
    private final SimpleSessionService sessionService;
    private final SimpleTicketService ticketService;

    public TicketController(TicketService tickets, SimpleFilmService filmService, SimpleSessionService sessionService,
                            SimpleTicketService ticketService) {
        this.tickets = tickets;
        this.filmService = filmService;
        this.sessionService = sessionService;
        this.ticketService = ticketService;
    }

    /* @GetMapping("/buy")
    public String getBuyPage(Model model, int id) {
        model.addAttribute("event", filmService.findByIdDto(id));
        return "tickets/buy";
    }*/

    @GetMapping("/{id}")
//public String getById(Model model, @PathVariable int id)
    public String getById(Model model, @PathVariable int id) {
       //var vacancyOptional = vacancyService.findById(id);
        SessionDto sessionDto = sessionService.findByIdSessionDto(id);
        //Ticket ticket = ticketService.findById(id).get();
        model.addAttribute("event", sessionDto);
        //model.addAttribute("ticket",ticket);
        return "tickets/buy";
    }
}
