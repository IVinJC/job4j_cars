package ru.job4j.carsale.repository;

import lombok.RequiredArgsConstructor;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.junit.Test;
import ru.job4j.carsale.model.Body;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;


public class BodyDbStoreTest {
    private final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
            .configure().build();
    private final SessionFactory sf = new MetadataSources(registry).buildMetadata().buildSessionFactory();
    private final BaseRepository baseRepository = new BaseRepository(sf);
    @Test
    public void create() {
        BodyDbStore store = new BodyDbStore(baseRepository);
        Body body = new Body("test");
        Body res = store.create(body);
        assertThat(res.getName(), is(body.getName()));
    }

    @Test
    public void findAll() {
        BodyDbStore store = new BodyDbStore(baseRepository);
        Body body = new Body("test");
        Body body2 = new Body("test2");
        store.create(body);
        store.create(body2);
        List<Body> res = store.findAll();
        assertThat(res, is(List.of(body, body2)));
    }

    @Test
    public void findById() {
        BodyDbStore store = new BodyDbStore(baseRepository);
        Body body = new Body("test");
        Body body2 = new Body("test2");
        store.create(body);
        store.create(body2);
        Body res = store.findById(2);
        assertThat(res, is(body2));
    }
}