package ru.job4j.carsale.repository;

import lombok.RequiredArgsConstructor;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.junit.Test;
import ru.job4j.carsale.model.Model;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class ModelDbStoreTest {
    private final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
            .configure().build();
    private final SessionFactory sf = new MetadataSources(registry).buildMetadata().buildSessionFactory();
    private final BaseRepository baseRepository = new BaseRepository(sf);
    @Test
    public void create() {
        ModelDbStore store = new ModelDbStore(baseRepository);
        Model model = new Model();
        model.setName("model");
        Model res = store.create(model);
        assertThat(res.getName(), is(model.getName()));
    }

    @Test
    public void findAll() {
        ModelDbStore store = new ModelDbStore(baseRepository);
        Model model = new Model("test");
        Model model2 = new Model("test2");
        store.create(model);
        store.create(model2);
        List<Model> res = store.findAll();
        assertThat(res, is(List.of(model, model2)));
    }

    @Test
    public void findById() {
        ModelDbStore store = new ModelDbStore(baseRepository);
        Model model = new Model("test");
        Model model2 = new Model("test2");
        store.create(model);
        store.create(model2);
        Model res = store.findById(2);
        assertThat(res, is(model2));
    }
}