package ru.job4j.carsale.repository;

import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import ru.job4j.carsale.model.Body;

import java.io.Serializable;
import java.util.List;
import java.util.function.Function;

@Repository
@RequiredArgsConstructor
public class BodyDbStore {
    private final SessionFactory sf;

    public Body create(Body body) {
        tx(session -> session.save(body));
        return body;
    }

    public List<Body> findAll() {
        return tx(
                session -> session.createQuery("from ru.job4j.carsale.model.Body order by id asc", Body.class)
                        .getResultList()
        );
    }

    public Body findById(int id) {
        return tx(session -> session.createQuery(
                        "from ru.job4j.carsale.model.Body a where a.id = :fId", Body.class)
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
