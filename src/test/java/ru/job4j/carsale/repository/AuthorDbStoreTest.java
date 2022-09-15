package ru.job4j.carsale.repository;

import lombok.RequiredArgsConstructor;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.junit.Test;
import ru.job4j.carsale.model.Author;
import ru.job4j.carsale.model.Author;

import java.util.List;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class AuthorDbStoreTest {
    private final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
            .configure().build();
    private final SessionFactory sf = new MetadataSources(registry).buildMetadata().buildSessionFactory();
    private final BaseRepository baseRepository = new BaseRepository(sf);

    @Test
    public void create() {
            AuthorDbStore store = new AuthorDbStore(baseRepository);
            Author author = new Author("name", "email", "phone");
            Author res = store.create(author);
            assertThat(res.getName(), is(author.getName()));
    }

    @Test
    public void findAll() {
        AuthorDbStore store = new AuthorDbStore(baseRepository);
        Author author = new Author();
        author.setName("test");
        Author author1 = new Author();
        author1.setName("test2");
        store.create(author);
        store.create(author1);
        List<Author> res = store.findAll();
        assertThat(res, is(List.of(author, author1)));
    }

    @Test
    public void findById() {
        AuthorDbStore store = new AuthorDbStore(baseRepository);
        Author author1 = new Author();
        author1.setName("test");
        Author author2 = new Author();
        author2.setName("test2");
        store.create(author2);
        store.create(author2);
        Author res = store.findById(2);
        assertThat(res, is(author2));
    }
}