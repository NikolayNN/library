package my.hhorushko.otus.library.service;

import my.hhorushko.otus.library.dao.AuthorRepository;
import my.hhorushko.otus.library.domain.Author;
import my.hhorushko.otus.library.service.impl.AuthorServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class AuthorServiceImplTest {

    @Mock
    private AuthorRepository authorRepository;

    @Captor
    private ArgumentCaptor<Author> authorArgumentCaptor;
    @Captor
    private ArgumentCaptor<Integer> integerArgumentCaptor;
    @Captor
    private ArgumentCaptor<String> stringArgumentCaptor;

    private AuthorService authorService;

    @Before
    public void setUp() throws Exception {
        authorService = new AuthorServiceImpl(authorRepository);
    }

    @Test
    public void findById() {

        int givenId = 1;
        Author expectAuthor = buildAuthor(givenId);

        when(authorRepository.findById(anyInt())).thenReturn(Optional.of(expectAuthor));

        Author actualAuthor = authorService.getById(givenId);

        assertEquals(expectAuthor, actualAuthor);
        verify(authorRepository).findById(integerArgumentCaptor.capture());
        assertEquals(givenId, integerArgumentCaptor.getValue(), 0);

        verifyNoMoreInteractions(authorRepository);
    }

    @Test
    public void findByName() {

        int givenId = 1;
        String givenName = "test_name";
        Author expectAuthor = buildAuthor(givenId, givenName);

        when(authorRepository.findByName(anyString())).thenReturn(Optional.of(expectAuthor));

        Author actualAuthor = authorService.getByName(givenName);

        assertEquals(expectAuthor, actualAuthor);
        verify(authorRepository).findByName(stringArgumentCaptor.capture());
        assertEquals(givenName, stringArgumentCaptor.getValue());

        verifyNoMoreInteractions(authorRepository);
    }

    @Test
    public void deleteById() {

        int givenId = 1;

        when(authorRepository.findById(anyInt())).thenReturn(Optional.of(new Author()));

        authorService.deleteById(givenId);

        verify(authorRepository).findById(integerArgumentCaptor.capture());
        assertEquals(givenId, integerArgumentCaptor.getValue(), 0);

        verify(authorRepository).deleteById(integerArgumentCaptor.capture());
        assertEquals(givenId, integerArgumentCaptor.getValue(), 0);

        verifyNoMoreInteractions(authorRepository);
    }

    @Test
    public void updateById() {

        int givenId = 1;
        String givenName = "test_name";
        Author expectAuthor = buildAuthor(givenId, givenName);

        when(authorRepository.save(any(Author.class))).thenReturn(expectAuthor);
        when(authorRepository.findById(anyInt())).thenReturn(Optional.of(expectAuthor));

        Author actualAuthor = authorService.updateById(givenId, new Author(givenName));
        assertEquals(expectAuthor, actualAuthor);

        verify(authorRepository).findById(integerArgumentCaptor.capture());
        assertEquals(givenId, integerArgumentCaptor.getValue(), 0);

        verify(authorRepository).save(authorArgumentCaptor.capture());
        assertEquals(expectAuthor, authorArgumentCaptor.getValue());

        verifyNoMoreInteractions(authorRepository);
    }

    @Test
    public void save() {

        int givenId = 1;
        String givenName = "test_name";
        Author expectAuthor = buildAuthor(givenId, givenName);

        when(authorRepository.save(any(Author.class))).thenReturn(expectAuthor);

        Author actualAuthor = authorService.save(new Author(givenName));

        assertEquals(expectAuthor, actualAuthor);
        verify(authorRepository).save(authorArgumentCaptor.capture());
        assertEquals(new Author(givenName), authorArgumentCaptor.getValue());

        verifyNoMoreInteractions(authorRepository);
    }

    @Test
    public void getAll() {

        List<Author> expectAuthors = buildAuthorList(3);
        when(authorRepository.findAll()).thenReturn(expectAuthors);

        List<Author> actualAuthors = authorService.getAll();

        assertEquals(expectAuthors, actualAuthors);
        verify(authorRepository, times(1)).findAll();

        verifyNoMoreInteractions(authorRepository);
    }

    private Author buildAuthor(int id) {
        Author author = new Author();
        author.setId(id);
        author.setName("test_name");
        return author;
    }

    private Author buildAuthor(int id, String name) {
        Author author = new Author();
        author.setId(id);
        author.setName(name);
        return author;
    }

    private List<Author> buildAuthorList(int count) {

        List<Author> result = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            Author author = new Author();
            author.setId(i);
            author.setName("test_name" + i);
            result.add(author);
        }
        return result;
    }
}