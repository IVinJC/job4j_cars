package ru.job4j.carsale.repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import ru.job4j.carsale.model.Ad;

import java.util.List;
import java.util.function.Function;

@Repository
public class AdDbStore {
    private final SessionFactory sf;

    public AdDbStore(SessionFactory sf) {
        this.sf = sf;
    }

    public Ad create(Ad ad) {
        tx(session -> session.save(ad));
        return ad;
    }

    public List<Ad> findAll() {
        return tx(
                session -> session.createQuery("from Ad order by id asc", Ad.class)
                        .getResultList());
    }

    public Ad findById(int id) {
        return tx(session -> session.createQuery(
                        "from ru.job4j.carsale.model.Ad a where a.id = :fId", Ad.class)
                .setParameter("fId", id).getSingleResult());
    }

    public Ad update(int id, Ad ad) {
        ad.setId(id);
        tx(session -> session.merge(ad));
        return ad;
    }

    public void delete(int id) {
        tx(session -> session.createQuery("DELETE Ad t WHERE t.id = :fId")
                .setParameter("fId", id)
                .executeUpdate());
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
