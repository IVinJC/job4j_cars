package ru.job4j.carsale.repository;

import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import ru.job4j.carsale.model.Body;
import ru.job4j.carsale.model.Mark;

import java.util.List;
import java.util.function.Function;

@Repository
@RequiredArgsConstructor
public class MarkDbStore {
    private final BaseRepository baseRepository;

    public Mark create(Mark mark) {
        baseRepository.tx(session -> session.save(mark));
        return mark;
    }

    public List<Mark> findAll() {
        return baseRepository.tx(
                session -> session.createQuery(
                                "select distinct m from Mark m join fetch m.models", Mark.class)
                        .getResultList());
    }

    public Mark findById(int id) {
        return baseRepository.tx(
                session -> session.createQuery(
                                "select distinct m from Mark m join fetch m.models where m.id = :fId", Mark.class)
                        .setParameter("fId", id).getSingleResult());
    }
}
