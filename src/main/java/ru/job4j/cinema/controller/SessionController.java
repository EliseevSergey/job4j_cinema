package ru.job4j.cinema.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.job4j.cinema.service.SimpleSessionService;

@Controller
@RequestMapping("/sessions")
public class SessionController {
    //private final SimpleSessionService sessionService = new SimpleSessionService();
    private final SimpleSessionService sessionService;

    public SessionController(SimpleSessionService sessionService) {
        this.sessionService = sessionService;
    }

    @GetMapping
    public String findAllSessionDto(Model model) {
        model.addAttribute("sessions", sessionService.findAllSessionDto());
        return "sessions/list";
    }
}
