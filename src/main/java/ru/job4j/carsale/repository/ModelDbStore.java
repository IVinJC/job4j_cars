package ru.job4j.carsale.repository;

import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import ru.job4j.carsale.model.Mark;
import ru.job4j.carsale.model.Model;

import java.util.List;
import java.util.function.Function;

@Repository
@RequiredArgsConstructor
public class ModelDbStore {
    private final BaseRepository baseRepository;

    public Model create(Model model) {
        baseRepository.tx(session -> session.save(model));
        return model;
    }

    public List<Model> findAll() {
        return baseRepository.tx(
                session -> session.createQuery("from ru.job4j.carsale.model.Model order by id asc", Model.class)
                        .getResultList());
    }

    public Model findById(int id) {
        return baseRepository.tx(session -> session.createQuery(
                        "from ru.job4j.carsale.model.Model a where a.id = :fId", Model.class)
                .setParameter("fId", id).getSingleResult());
    }
}
