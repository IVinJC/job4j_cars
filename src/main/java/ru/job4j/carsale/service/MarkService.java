package ru.job4j.carsale.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.carsale.model.Mark;
import ru.job4j.carsale.repository.MarkDbStore;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MarkService {
    private final MarkDbStore markDbStore;

    public List<Mark> findAll() {
        return markDbStore.findAll();
    }

    public Mark findById(int id) {
        return markDbStore.findById(id);
    }
}
