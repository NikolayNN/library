package my.hhorushko.otus.library.dao;

import my.hhorushko.otus.library.domain.Author;
import my.hhorushko.otus.library.domain.Book;
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
import java.util.Arrays;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class BookRepositoryTest {

    @Autowired
    private TestEntityManager testEntityManager;
    @Autowired
    private BookRepository bookRepository;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    private Genre dbGenre;
    private Author dbAuthor1;
    private Author dbAuthor2;

    @Before
    public void setUp() throws Exception {
        dbGenre = testEntityManager.persist(new Genre("test_genreName"));
        dbAuthor1= testEntityManager.persist(new Author("test_authorName1"));
        dbAuthor2= testEntityManager.persist(new Author("test_authorName2"));
    }

    @Test
    public void findByNameTest() {

        String givenBookName = "test_bookName";
        Book givenBook = new Book(givenBookName, dbGenre, Arrays.asList(dbAuthor1, dbAuthor2));
        givenBook.setId(testEntityManager.persistAndGetId(givenBook, Integer.class));

        Book actualBook = bookRepository.findByName(givenBookName).get();

        assertEquals(givenBook, actualBook);
    }

    @Test
    public void throwExceptionIfBookNameIsBlank() {

        String givenBookName = "";
        Book givenBook = new Book(givenBookName, dbGenre, Arrays.asList(dbAuthor1, dbAuthor2));
        givenBook.setId(testEntityManager.persistAndGetId(givenBook, Integer.class));

        //then
        thrown.expect(ConstraintViolationException.class);
        thrown.expectMessage("interpolatedMessage='не может быть пусто', propertyPath=name,");

        bookRepository.findByName(givenBookName).get();
    }

    @Test
    public void throwExceptionIfBookNameIsNull() {

        String givenBookName = null;
        Book givenBook = new Book(givenBookName, dbGenre, Arrays.asList(dbAuthor1, dbAuthor2));
        givenBook.setId(testEntityManager.persistAndGetId(givenBook, Integer.class));

        //then
        thrown.expect(ConstraintViolationException.class);
        thrown.expectMessage("interpolatedMessage='не может быть пусто', propertyPath=name,");

        bookRepository.findByName(givenBookName).get();
    }

    @Test
    public void throwExceptionIfBookGenreIsNull() {

        String givenBookName = "test_bookName";
        Book givenBook = new Book(givenBookName, null, Arrays.asList(dbAuthor1, dbAuthor2));
        givenBook.setId(testEntityManager.persistAndGetId(givenBook, Integer.class));

        //then
        thrown.expect(ConstraintViolationException.class);
        thrown.expectMessage("interpolatedMessage='должно быть задано', propertyPath=genre");

        bookRepository.findByName(givenBookName).get();
    }

    @Test
    public void throwExceptionIfBookAuthorListIsNull() {

        String givenBookName = "test_bookName";
        Book givenBook = new Book(givenBookName, dbGenre, null);
        givenBook.setId(testEntityManager.persistAndGetId(givenBook, Integer.class));

        //then
        thrown.expect(ConstraintViolationException.class);
        thrown.expectMessage("interpolatedMessage='должно быть задано', propertyPath=authors");

        bookRepository.findByName(givenBookName).get();
    }
}