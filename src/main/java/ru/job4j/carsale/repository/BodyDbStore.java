package ru.job4j.carsale.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.job4j.carsale.model.Body;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class BodyDbStore {
    private final BaseRepository baseRepository;

    public Body create(Body body) {
        baseRepository.tx(session -> session.save(body));
        return body;
    }

    public List<Body> findAll() {
        return baseRepository.tx(
                session -> session.createQuery("from ru.job4j.carsale.model.Body order by id asc", Body.class)
                        .getResultList()
        );
    }

    public Body findById(int id) {
        return baseRepository.tx(session -> session.createQuery(
                        "from ru.job4j.carsale.model.Body a where a.id = :fId", Body.class)
                .setParameter("fId", id).getSingleResult());
    }
}
