package ru.job4j.carsale.repository;

import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import ru.job4j.carsale.model.Author;
import ru.job4j.carsale.model.Author;
import ru.job4j.carsale.model.Mark;

import java.util.List;
import java.util.function.Function;

@Repository
@RequiredArgsConstructor
public class AuthorDbStore {
    private final BaseRepository baseRepository;

    public Author create(Author author) {
        baseRepository.tx(session -> session.save(author));
        return author;
    }

    public List<Author> findAll() {
        return baseRepository.tx(
                session -> session.createQuery("from ru.job4j.carsale.model.Author order by id asc", Author.class)
                        .getResultList()
        );
    }

    public Author findById(int id) {
        return baseRepository.tx(session -> session.createQuery(
                        "from ru.job4j.carsale.model.Author a where a.id = :fId", Author.class)
                .setParameter("fId", id).getSingleResult());
    }
}
