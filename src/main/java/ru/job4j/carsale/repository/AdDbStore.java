package ru.job4j.carsale.repository;

import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import ru.job4j.carsale.model.Ad;

import java.util.List;
import java.util.function.Function;

@Repository
@RequiredArgsConstructor
public class AdDbStore {
    private final BaseRepository baseRepository;

    public Ad create(Ad ad) {
        baseRepository.tx(session -> session.save(ad));
        return ad;
    }

    public List<Ad> findAll() {
        return baseRepository.tx(
                session -> session.createQuery("from Ad order by id asc", Ad.class)
                        .getResultList());
    }

    public Ad findById(int id) {
        return baseRepository.tx(session -> session.createQuery(
                        "from ru.job4j.carsale.model.Ad a where a.id = :fId", Ad.class)
                .setParameter("fId", id).getSingleResult());
    }

    public Ad update(int id, Ad ad) {
        ad.setId(id);
        baseRepository.tx(session -> session.merge(ad));
        return ad;
    }

    public void delete(int id) {
        baseRepository.tx(session -> session.createQuery("DELETE Ad t WHERE t.id = :fId")
                .setParameter("fId", id)
                .executeUpdate());
    }
}
