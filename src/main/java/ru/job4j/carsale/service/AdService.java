package ru.job4j.carsale.service;

import org.springframework.stereotype.Service;
import ru.job4j.carsale.model.Ad;
import ru.job4j.carsale.repository.AdDbStore;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AdService {
    private final AdDbStore adDbStore;
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMM-yyyy hh:mm");

    public AdService(AdDbStore adDbStore) {
        this.adDbStore = adDbStore;
    }

    public Ad create(Ad ad) {
        return adDbStore.create(ad);
    }

    public List<Ad> findAll() {
        return adDbStore.findAll()
                .stream()
                .peek(ad -> ad.setFormattedDate(ad.getCreated().format(formatter)))
                .collect(Collectors.toList());
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
