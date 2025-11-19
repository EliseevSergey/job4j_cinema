package ru.job4j.cinema.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.job4j.cinema.dto.SessionDto;
import ru.job4j.cinema.model.Ticket;
import ru.job4j.cinema.service.*;

@Controller
@RequestMapping("/tickets")
public class TicketController {
    private final TicketService tickets;
    private final FilmService filmService;
    private final SessionService sessionService;

    public TicketController(TicketService tickets, FilmService filmService, SessionService sessionService) {
        this.tickets = tickets;
        this.filmService = filmService;
        this.sessionService = sessionService;
    }

    @GetMapping("/{id}")
    public String getPurchasePage(Model model, @PathVariable int id) {
        SessionDto sessionDto = sessionService.findByIdSessionDto(id);
        model.addAttribute("event", sessionDto);
        model.addAttribute("hall", sessionService.findHallById(sessionDto.getHallId()));
        return "tickets/buy";
    }

    /*@PostMapping("/buy")
    public String buyTicket(@ModelAttribute Ticket ticket, Model model) {
        try {
            System.out.println("-==!!!!!!!!!!=---" + ticket);
            tickets.buy(ticket);
            model.addAttribute("ticket", ticket);
            return "tickets/successful";
        } catch (Exception exception) {
            model.addAttribute("message", exception.getMessage());
            return "errors/404";
        }
    }*/

    @PostMapping("/buy")
    public String buyTicket(@ModelAttribute Ticket ticket, Model model) {
        var savedTicket = tickets.buy(ticket);
        if (savedTicket.isEmpty()) {
            model.addAttribute("message", "Не удалось приобрести билет на заданное место." +
                    " Вероятно оно уже занято. Перейдите на страницу бронирования билетов и попробуйте снова");
            return "errors/404";
        } else {
            model.addAttribute("ticket", ticket);
            return "tickets/successful";
        }
    }
}

