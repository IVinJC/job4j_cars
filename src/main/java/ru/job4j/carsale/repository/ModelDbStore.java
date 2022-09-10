package ru.job4j.carsale.repository;

import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import ru.job4j.carsale.model.Model;

import java.util.List;
import java.util.function.Function;

@Repository
@RequiredArgsConstructor
public class ModelDbStore {
    private final SessionFactory sf;

    public List<Model> findAll() {
        return tx(
                session -> session.createQuery("from ru.job4j.carsale.model.Model order by id asc", Model.class)
                        .getResultList());
    }

    public Model findById(int id) {
        return tx(session -> session.createQuery(
                        "from ru.job4j.carsale.model.Model a where a.id = :fId", Model.class)
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
