package ru.job4j.carsale.repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.junit.Test;
import ru.job4j.carsale.model.Ad;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
public class AdDbStoreTest {
    private final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
            .configure().build();
    private final SessionFactory sf = new MetadataSources(registry).buildMetadata().buildSessionFactory();
    private final BaseRepository baseRepository = new BaseRepository(sf);

    @Test
    public void create() {
        AdDbStore store = new AdDbStore(baseRepository);
        Ad ad = new Ad();
        ad.setName("test");
        Ad res = store.create(ad);
        assertThat(res.getName(), is(ad.getName()));
    }

    @Test
    public void findAll() {
        AdDbStore store = new AdDbStore(baseRepository);
        Ad ad = new Ad();
        ad.setName("test");
        Ad ad2 = new Ad();
        ad2.setName("test2");
        store.create(ad);
        store.create(ad2);
        List<Ad> res = store.findAll();
        assertThat(res, is(List.of(ad, ad2)));
    }

    @Test
    public void findById() {
        AdDbStore store = new AdDbStore(baseRepository);
        Ad ad1 = new Ad();
        ad1.setName("test");
        Ad ad2 = new Ad();
        ad2.setName("test2");
        store.create(ad1);
        store.create(ad2);
        Ad res = store.findById(2);
        assertThat(res, is(ad2));
    }

    @Test
    public void update() {
        AdDbStore store = new AdDbStore(baseRepository);
        Ad ad1 = new Ad();
        ad1.setName("test");
        Ad ad2 = new Ad();
        ad2.setName("test2");
        store.create(ad1);
        store.create(ad2);
        Ad ad3 = new Ad();
        ad3.setName("test3");
        store.update(2, ad3);
        List<Ad> res = store.findAll();
        assertThat(res, is(List.of(ad1, ad3)));
    }

    @Test
    public void delete() {
        AdDbStore store = new AdDbStore(baseRepository);
        Ad ad1 = new Ad();
        ad1.setName("test");
        Ad ad2 = new Ad();
        ad2.setName("test2");
        store.create(ad1);
        store.create(ad2);
        store.delete(1);
        List<Ad> res = store.findAll();
        assertThat(res, is(List.of(ad2)));
    }
}