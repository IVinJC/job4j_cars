package ru.job4j.carsale.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.carsale.model.Model;
import ru.job4j.carsale.repository.ModelDbStore;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ModelService {
    private final ModelDbStore modelDbStore;

    public List<Model> findAll() {
        return modelDbStore.findAll();
    }

    public Model findById(int id) {
        return modelDbStore.findById(id);
    }
}
