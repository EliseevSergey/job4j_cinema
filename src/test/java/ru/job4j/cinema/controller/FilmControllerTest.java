package ru.job4j.cinema.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.ui.ConcurrentModel;
import org.springframework.web.multipart.MultipartFile;
import ru.job4j.cinema.dto.FilmDto;
import ru.job4j.cinema.service.FilmService;

import java.util.Collection;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class FilmControllerTest {
    private FilmService filmService;
    private FilmController filmController;
    private MultipartFile testFile;

    @BeforeEach
    public void init() {
        filmService = mock(FilmService.class);

        filmController = new FilmController(filmService);
        testFile = new MockMultipartFile("test.img", new byte[] {1, 2, 3});
    }

    @Test
    public void whenRequestFilmListThenGetAll() {
        FilmDto filmDto1 = new FilmDto(1, "First", "Description1", 2026, 1, 60, "Фентази", 1);
        FilmDto filmDto2 = new FilmDto(2, "Second", "Description2", 2026, 1, 60, "Боевик", 2);
        Collection<FilmDto> expectedFilmDto = List.of(filmDto1, filmDto2);
        ConcurrentModel model = new ConcurrentModel();

        when(filmService.findAllFilmDto()).thenReturn(expectedFilmDto);

        String view = filmController.getAll(model);
        Object actualPreviews = model.getAttribute("previews");

        assertThat(view).isEqualTo("films/list");
        assertThat(actualPreviews).isEqualTo(expectedFilmDto);
    }
}