package my.hhorushko.otus.library.dao;

import my.hhorushko.otus.library.domain.Author;
import my.hhorushko.otus.library.domain.Genre;
import org.junit.Before;
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

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class AuthorRepositoryTest {

    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private AuthorRepository authorRepository;

    @Rule
    public ExpectedException thrown = ExpectedException.none();


    @Test
    public void findByNameTest() throws Exception {

        final String givenAuthorName = "test_authorName";
        Author givenAuthor = new Author(givenAuthorName);
        givenAuthor.setId(testEntityManager.persistAndGetId(givenAuthor, Integer.class));

        Author actualAuthor = authorRepository.findByName(givenAuthorName).get();

        assertEquals(givenAuthor, actualAuthor);
    }

    @Test
    public void throwExceptionIfNameIsBlank() throws Exception {

        final String givenAuthorName = "";
        Author givenAuthor = new Author(givenAuthorName);
        givenAuthor.setId(testEntityManager.persistAndGetId(givenAuthor, Integer.class));

        //then
        thrown.expect(ConstraintViolationException.class);
        thrown.expectMessage("interpolatedMessage='не может быть пусто', propertyPath=name,");

        authorRepository.findByName(givenAuthorName).get();
    }

    @Test
    public void throwExceptionIfNameIsBlank2() throws Exception {

        final String givenAuthorName = "      ";
        Author givenAuthor = new Author(givenAuthorName);
        givenAuthor.setId(testEntityManager.persistAndGetId(givenAuthor, Integer.class));

        //then
        thrown.expect(ConstraintViolationException.class);
        thrown.expectMessage("interpolatedMessage='не может быть пусто', propertyPath=name,");

        authorRepository.findByName(givenAuthorName).get();
    }

    @Test
    public void throwExceptionIfNameIsNull() throws Exception {

        final String givenAuthorName = null;
        Author givenAuthor = new Author(givenAuthorName);
        givenAuthor.setId(testEntityManager.persistAndGetId(givenAuthor, Integer.class));

        //then
        thrown.expect(ConstraintViolationException.class);
        thrown.expectMessage("interpolatedMessage='не может быть пусто', propertyPath=name,");

        authorRepository.findByName(givenAuthorName).get();
    }
}