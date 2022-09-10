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
    private final SessionFactory sf;

    public Author create(Author author) {
        tx(session -> session.save(author));
        return author;
    }

    public List<Author> findAll() {
        return tx(
                session -> session.createQuery("from ru.job4j.carsale.model.Author order by id asc", Author.class)
                        .getResultList()
        );
    }

    public Author findById(int id) {
        return tx(session -> session.createQuery(
                        "from ru.job4j.carsale.model.Author a where a.id = :fId", Author.class)
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
