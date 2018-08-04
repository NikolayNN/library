package my.hhorushko.otus.library.dao;

import my.hhorushko.otus.library.domain.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class CommentRepositoryTest {

    @Autowired
    private TestEntityManager testEntityManager;
    @Autowired
    private CommentRepository commentRepository;

    private Book dbBook;
    private User dbUser;

    @Before
    public void setUp() throws Exception {

        dbBook = testEntityManager.persist(new Book("book_name",
                testEntityManager.persist(new Genre("genre_name")),
                Arrays.asList(testEntityManager.persist(new Author("name")))));
        dbUser = testEntityManager.persist(new User("username"));
    }

    @Test
    public void testSaveComment() {

        Comment givenComment = new Comment("comment", dbBook, dbUser);
        givenComment.setId(testEntityManager.persistAndGetId(givenComment, Integer.class));

        Comment actualComment = commentRepository.findById(givenComment.getId()).get();

        assertEquals(givenComment, actualComment);
    }
}