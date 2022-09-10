package ru.job4j.carsale.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.carsale.model.Body;
import ru.job4j.carsale.repository.BodyDbStore;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BodyService {
    private final BodyDbStore bodyDbStore;

    public Body create(Body body) {
        return bodyDbStore.create(body);
    }

    public List<Body> findAll() {
        return bodyDbStore.findAll();
    }

    public Body findById(int id) {
        return bodyDbStore.findById(id);
    }
}
