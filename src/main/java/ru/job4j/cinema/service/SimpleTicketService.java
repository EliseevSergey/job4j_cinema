package ru.job4j.cinema.service;

import org.springframework.stereotype.Service;
import ru.job4j.cinema.model.Ticket;
import ru.job4j.cinema.repository.TicketRepository;

import java.util.Optional;

@Service
public class SimpleTicketService implements TicketService {
    private final TicketRepository tickets;

    public SimpleTicketService(TicketRepository sql2oTicketsRepository) {
        this.tickets = sql2oTicketsRepository;
    }

    @Override
    public Optional<Ticket> buy(Ticket ticket) {
        return tickets.save(ticket);
    }

    @Override
    public Optional<Ticket> findById(int id) {
        return tickets.findById(id);
    }
}
