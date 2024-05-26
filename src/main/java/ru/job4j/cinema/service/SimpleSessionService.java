package ru.job4j.cinema.service;

import org.springframework.stereotype.Service;
import ru.job4j.cinema.dto.SessionDto;
import ru.job4j.cinema.model.FilmSession;
import ru.job4j.cinema.repository.*;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class SimpleSessionService implements SessionService {
    private final SessionRepository sessions = new MemorySessionRepository();
    private final HallRepository halls = new MemoryHallRepository();
    private final FilmRepository films = new MemoryFilmRepository();

    @Override
    public Collection<FilmSession> findAll() {
        return sessions.findAll();
    }

    public Collection<SessionDto> findAllSessionDto() {
        return sessions.findAll().stream().map((s) -> new SessionDto(s.getId(),
                        s.getFilmId(), films.findById(s.getFilmId()).get().getName(),
                        halls.findById(s.getHallId()).getName(),
                        films.findById(s.getFilmId()).get().getMinAge(),
                        s.getStartTime(), s.getEndTime(), s.getPrice()))
                .collect(Collectors.toList());
    }

    @Override
    public SessionDto findByIdSessionDto(int id) {
        FilmSession filmSession = sessions.findById(id);
        return new SessionDto(filmSession.getId(), filmSession.getFilmId(), films.findById(filmSession.getFilmId()).get().getName(),
                halls.findById(filmSession.getHallId()).getName(),
                films.findById(filmSession.getFilmId()).get().getMinAge(),
                filmSession.getStartTime(), filmSession.getEndTime(), filmSession.getPrice());

    }

}
