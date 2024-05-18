package ru.job4j.cinema.repository;

import ru.job4j.cinema.model.Hall;

import java.util.HashMap;
import java.util.Map;

public class MemoryHallRepository implements HallRepository {
    private final Map<Integer, Hall> halls = new HashMap<>();

    public MemoryHallRepository() {
        halls.put(0, new Hall(0, "Red", 10, 10, "Small"));
        halls.put(1, new Hall(1, "Blue", 5, 5, "Middle"));
        halls.put(2, new Hall(2, "Green", 20, 20, "Big"));
    }

    @Override
    public Hall findById(int id) {
        return halls.get(id);
    }
}
