package ru.job4j.carsale.repository;

import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import ru.job4j.carsale.model.Mark;

import java.util.List;
import java.util.function.Function;

@Repository
@RequiredArgsConstructor
public class MarkDbStore {
    private final SessionFactory sf;

    public List<Mark> findAll() {
        return tx(
                session -> session.createQuery(
                                "select distinct m from Mark m join fetch m.models", Mark.class)
                        .getResultList());
    }

    public Mark findById(int id) {
        return tx(
                session -> session.createQuery(
                                "select distinct m from Mark m join fetch m.models where m.id = :fId", Mark.class)
                        .setParameter("fId", id).getSingleResult());
    }

    private <T> T tx(Function<Session, T> command) {
        var session = sf.openSession();
        try (session) {
            var tx = session.beginTransaction();
            T rsl = command.apply(session);
            tx.commit();
            session.close();
            return rsl;
        } catch (Exception e) {
            var tx = session.getTransaction();
            if (tx.isActive()) {
                tx.rollback();
            }
            throw e;
        }
    }
}
