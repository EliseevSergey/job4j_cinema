package ru.job4j.cinema.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.ui.ConcurrentModel;
import ru.job4j.cinema.dto.SessionDto;
import ru.job4j.cinema.service.SessionService;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class SessionControllerTest {
    private SessionService sessionService;
    private SessionController sessionController;

    @BeforeEach
    public void init() {
        sessionService = mock(SessionService.class);
        sessionController = new SessionController(sessionService);
    }

    @Test
    public void whenRequestAllSessionDtoThenGetAll() {
        SessionDto sessionDto1 = new SessionDto(1, 1, "Film", 1, "Red", 18,
                LocalDateTime.of(2024, 5, 14, 10, 0),
                LocalDateTime.of(2024, 5, 14, 12, 0), 100);

        SessionDto sessionDto2 = new SessionDto(1, 1, "Film2", 1, "Blue", 18,
                LocalDateTime.of(2024, 5, 14, 10, 0),
                LocalDateTime.of(2024, 5, 14, 12, 0), 200);

        List<SessionDto> expectedSessionDto = List.of(sessionDto1, sessionDto2);
        when(sessionService.findAllSessionDto()).thenReturn(expectedSessionDto);
        ConcurrentModel model = new ConcurrentModel();

        String view = sessionController.findAllSessionDto(model);
        Object actualSessionDto = model.getAttribute("sessions");
        assertThat(view).isEqualTo("sessions/list");
        assertThat(actualSessionDto).isEqualTo(expectedSessionDto);
    }
}