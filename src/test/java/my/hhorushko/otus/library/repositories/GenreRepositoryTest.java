package my.hhorushko.otus.library.repositories;

import my.hhorushko.otus.library.dao.GenreRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;

@SpringBootTest
@RunWith(SpringRunner.class)
@DataJpaTest
public class GenreRepositoryTest {

    @Autowired
    EntityManager entityManager;

    private GenreRepository genreRepository;

    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void test(){

    }
}
