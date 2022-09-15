package ru.job4j.carsale.repository;

import lombok.RequiredArgsConstructor;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.junit.Ignore;
import org.junit.Test;
import ru.job4j.carsale.model.Mark;
import ru.job4j.carsale.model.Model;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class MarkDbStoreTest {
    private final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
            .configure().build();
    private final SessionFactory sf = new MetadataSources(registry).buildMetadata().buildSessionFactory();
    private final BaseRepository baseRepository = new BaseRepository(sf);

    @Test
    public void create() {
        MarkDbStore store = new MarkDbStore(baseRepository);
        Mark mark = new Mark();
        mark.setName("mark");
        Mark res = store.create(mark);
        assertThat(res.getName(), is(mark.getName()));
    }

    @Test
    @Ignore
    public void findAll() {
        MarkDbStore store = new MarkDbStore(baseRepository);
        Mark mark = new Mark();
        mark.setName("mark");
        mark.setModels(List.of(new Model()));
        Mark mark2 = new Mark();
        mark2.setName("mark2");
        mark2.setModels(List.of(new Model()));
        store.create(mark);
        store.create(mark2);
        List<Mark> res = store.findAll();
        assertThat(res, is(List.of(mark, mark2)));
    }

    @Test
    @Ignore
    public void findById() {
        MarkDbStore storeMark = new MarkDbStore(baseRepository);
        Mark mark = new Mark("mark");
        mark.setModels(List.of(new Model("mark")));
        storeMark.create(mark);
        Mark res = storeMark.findById(1);
        assertThat(res, is(mark));
    }
}