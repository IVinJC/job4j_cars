package ru.job4j.carsale.service;

import org.springframework.stereotype.Service;
import ru.job4j.carsale.model.Ad;
import ru.job4j.carsale.repository.AdDbStore;

import java.util.List;

@Service
public class AdService {
    private final AdDbStore adDbStore;

    public AdService(AdDbStore adDbStore) {
        this.adDbStore = adDbStore;
    }

    public Ad create(Ad ad) {
        return adDbStore.create(ad);
    }

    public List<Ad> findAll() {
        return adDbStore.findAll();
    }

    public Ad findById(int id) {
        return adDbStore.findById(id);
    }

    public Ad update(int id, Ad ad) {
        return adDbStore.update(id, ad);
    }

    public void delete(int id) {
        adDbStore.delete(id);
    }
}
