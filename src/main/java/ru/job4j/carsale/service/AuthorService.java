package ru.job4j.carsale.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.carsale.model.Author;
import ru.job4j.carsale.repository.AuthorDbStore;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthorService {
    private final AuthorDbStore authorDbStore;

    public Author create(Author author) {
        return authorDbStore.create(author);
    }

    public List<Author> findAll() {
        return authorDbStore.findAll();
    }

    public Author findById(int id) {
        return authorDbStore.findById(id);
    }
}
