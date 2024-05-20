package ru.job4j.cinema.service;

import ru.job4j.cinema.model.Ticket;
import ru.job4j.cinema.repository.TicketRepository;

import java.util.Optional;

public class SimpleTicketService implements TicketService {
    private final TicketRepository tickets;

    public SimpleTicketService(TicketRepository tickets) {
        this.tickets = tickets;
    }

    @Override
    public Optional<Ticket> buy(Ticket ticket) {
        return tickets.buy(ticket);
    }

    @Override
    public Optional<Ticket> findById(int id) {
        return tickets.findById(id);
    }
}
