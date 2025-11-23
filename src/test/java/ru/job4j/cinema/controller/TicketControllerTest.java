package ru.job4j.cinema.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.ui.ConcurrentModel;
import ru.job4j.cinema.dto.SessionDto;
import ru.job4j.cinema.model.Hall;
import ru.job4j.cinema.model.Ticket;
import ru.job4j.cinema.service.FilmService;
import ru.job4j.cinema.service.SessionService;
import ru.job4j.cinema.service.TicketService;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TicketControllerTest {
    private TicketController ticketController;
    private TicketService ticketService;
    private FilmService filmService;
    private SessionService sessionService;

    @BeforeEach
    public void init() {
        ticketService = mock(TicketService.class);
        filmService = mock(FilmService.class);
        sessionService = mock(SessionService.class);
        ticketController = new TicketController(ticketService, filmService, sessionService);
    }

    @Test
    public void whenGetPurchasePageThenGetBuyTicket() {
        int id = 1;
        SessionDto sessionDto1 = new SessionDto(1, 1, "Film", 1, "Red", 18,
                LocalDateTime.of(2024, 5, 14, 10, 0),
                LocalDateTime.of(2024, 5, 14, 12, 0), 100);
        when(sessionService.findByIdSessionDto(id)).thenReturn(sessionDto1);

        Hall hall1 = new Hall(0, "Red", 10, 10, "Small");
        when(sessionService.findHallById(sessionDto1.getHallId())).thenReturn(hall1);

        ConcurrentModel model = new ConcurrentModel();
        String view = ticketController.getPurchasePage(model, id);
        Object actualEvent = model.getAttribute("event");
        Object actualHall = model.getAttribute("hall");

        assertThat(view).isEqualTo("tickets/buy");
        assertThat(actualEvent).isEqualTo(sessionDto1);
        assertThat(actualHall).isEqualTo(hall1);
    }


    @Test
    void whenBuyTicketSuccessfulThenGetSuccessfullPage() {
        Ticket ticket1 = new Ticket(1, 1, 1, 1, 1);
        ArgumentCaptor<Ticket> ticketArgumentCaptor = ArgumentCaptor.forClass(Ticket.class);
        when(ticketService.buy(ticketArgumentCaptor.capture())).thenReturn(Optional.of(ticket1));

        ConcurrentModel model = new ConcurrentModel();

        String view = ticketController.buyTicket(ticket1, model);
        assertThat(view).isEqualTo("tickets/successful");

        Ticket actualTicket = ticketArgumentCaptor.getValue();
        assertThat(actualTicket).isEqualTo(ticket1);
    }

    @Test
    void whenBuyTicketFailureThenGetError() {
        Ticket ticket1 = new Ticket(1, 1, 1, 1, 1);
        ConcurrentModel model = new ConcurrentModel();
        String view = ticketController.buyTicket(ticket1, model);
        assertThat(view).isEqualTo("errors/404");
        Object actualExceptionMessage = model.getAttribute("message");
        assertThat(actualExceptionMessage).isEqualTo("Не удалось приобрести билет на заданное место."
                + " Вероятно оно уже занято. Перейдите на страницу бронирования билетов и попробуйте снова");
    }
}