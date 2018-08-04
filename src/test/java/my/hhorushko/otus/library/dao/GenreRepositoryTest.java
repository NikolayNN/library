package my.hhorushko.otus.library.dao;

import my.hhorushko.otus.library.domain.Author;
import my.hhorushko.otus.library.domain.Genre;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import javax.validation.ConstraintViolationException;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class GenreRepositoryTest {

    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private GenreRepository genreRepository;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void findByName() {

        final String givenGenreName = "test_genreName";
        Genre givenGenre = new Genre(givenGenreName);
        givenGenre.setId(testEntityManager.persistAndGetId(givenGenre, Integer.class));

        Genre actualGenre = genreRepository.findByName(givenGenreName).get();

        assertEquals(givenGenre, actualGenre);
    }

    @Test
    public void throwExceptionIfNameIsNull() throws Exception {

        final String givenGenreName = null;
        Genre givenGenre = new Genre(givenGenreName);
        givenGenre.setId(testEntityManager.persistAndGetId(givenGenre, Integer.class));

        //then
        thrown.expect(ConstraintViolationException.class);
        thrown.expectMessage("interpolatedMessage='не может быть пусто', propertyPath=name,");

        genreRepository.findByName(givenGenreName);
    }
}