package my.hhorushko.otus.library.shell;

import my.hhorushko.otus.library.domain.Author;
import my.hhorushko.otus.library.domain.Book;
import my.hhorushko.otus.library.domain.Genre;
import my.hhorushko.otus.library.service.*;
import my.hhorushko.otus.library.service.impl.AuthenticationServiceImpl;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class AppCommandsTest {

    @Mock
    private AuthorService authorService;
    @Mock
    private GenreService genreService;
    @Mock
    private BookService bookService;
    @Mock
    private AuthenticationServiceImpl authenticationService;
    @Mock
    private CommentService commentService;

    @Captor
    private ArgumentCaptor<Integer> integerArgumentCaptor;
    @Captor
    private ArgumentCaptor<String> stringArgumentCaptor;
    @Captor
    private ArgumentCaptor<Author> authorArgumentCaptor;
    @Captor
    private ArgumentCaptor<Genre> genreArgumentCaptor;

    private AppCommands appCommands;

    private final int defaultInt = -1;
    private final String defaultString = "";

    @Rule
    public ExpectedException thrown = ExpectedException.none();


    @Before
    public void setUp() throws Exception {
        appCommands = new AppCommands(authorService, genreService, bookService, authenticationService, commentService);
    }

    @Test
    public void authorFindIfIdSpecified() {

        final int givenId = 1;
        final Author expectAuthor = buildAuthor(givenId);
        when(authorService.getById(anyInt())).thenReturn(expectAuthor);

        String actualOutput = appCommands.authorFind(givenId, defaultString);

        assertEquals(expectAuthor.toString(), actualOutput);
        verify(authorService).getById(integerArgumentCaptor.capture());
        assertEquals(givenId, integerArgumentCaptor.getValue(), 0);
        verifyNoMoreInteractions(authorService);
        verifyZeroInteractions(bookService);
        verifyZeroInteractions(genreService);
        verifyZeroInteractions(commentService);
        verifyZeroInteractions(authenticationService);
    }

    @Test
    public void authorFindIfNameSpecified() {

        final int givenId = 1;
        final String givenName = "test_name";
        final Author expectAuthor = buildAuthor(givenId, givenName);
        when(authorService.getByName(anyString())).thenReturn(expectAuthor);

        String actualOutput = appCommands.authorFind(defaultInt, givenName);

        assertEquals(expectAuthor.toString(), actualOutput);
        verify(authorService).getByName(stringArgumentCaptor.capture());
        assertEquals(givenName, stringArgumentCaptor.getValue());
        verifyNoMoreInteractions(authorService);
        verifyZeroInteractions(bookService);
        verifyZeroInteractions(genreService);
        verifyZeroInteractions(commentService);
        verifyZeroInteractions(authenticationService);
    }

    @Test
    public void authorFindIfNameAndIdNotSpecified() {

        final List<Author> expectAuthors = buildAuthorList(3);
        when(authorService.getAll()).thenReturn(expectAuthors);

        String actualOutput = appCommands.authorFind(defaultInt, defaultString);

        assertEquals(expectAuthors.toString(), actualOutput);
        verify(authorService, times(1)).getAll();

        verifyNoMoreInteractions(authorService);
        verifyZeroInteractions(bookService);
        verifyZeroInteractions(genreService);
        verifyZeroInteractions(commentService);
        verifyZeroInteractions(authenticationService);
    }

    @Test
    public void authorDelete() {

        final int givenId = 1;
        final String expectOutput = "deleted";

        String actualOutput = appCommands.authorDelete(givenId);

        assertEquals(expectOutput, actualOutput);
        verify(authorService).deleteById(integerArgumentCaptor.capture());
        assertEquals(givenId, integerArgumentCaptor.getValue(), 0);
        verifyNoMoreInteractions(authorService);
        verifyZeroInteractions(bookService);
        verifyZeroInteractions(genreService);
        verifyZeroInteractions(commentService);
        verifyZeroInteractions(authenticationService);
    }

    @Test
    public void authorUpdate() {

        final int givenId = 1;
        final String givenAuthorName = "test_authorName";

        Author author = buildAuthor(givenId, givenAuthorName);

        final String expectOutput = "updated: " + author.toString();

        when(authorService.updateById(anyInt(), any(Author.class))).thenReturn(author);

        String actualOutput = appCommands.authorUpdate(givenId, givenAuthorName);

        assertEquals(expectOutput, actualOutput);
        verify(authorService).updateById(integerArgumentCaptor.capture(), authorArgumentCaptor.capture());

        assertEquals(givenId, integerArgumentCaptor.getValue(), 0);
        assertEquals(new Author(givenAuthorName), authorArgumentCaptor.getValue());

        verifyNoMoreInteractions(authorService);
        verifyZeroInteractions(bookService);
        verifyZeroInteractions(genreService);
        verifyZeroInteractions(commentService);
        verifyZeroInteractions(authenticationService);
    }

    @Test
    public void authorSave() {

        final int givenId = 1;
        final String givenAuthorName = "test_authorName";

        Author author = buildAuthor(givenId, givenAuthorName);

        final String expectOutput = "saved: " + author.toString();

        when(authorService.save(any(Author.class))).thenReturn(author);

        String actualOutput = appCommands.authorSave(givenAuthorName);

        assertEquals(expectOutput, actualOutput);
        verify(authorService).save(authorArgumentCaptor.capture());

        assertEquals(new Author(givenAuthorName), authorArgumentCaptor.getValue());

        verifyNoMoreInteractions(authorService);
        verifyZeroInteractions(bookService);
        verifyZeroInteractions(genreService);
        verifyZeroInteractions(commentService);
        verifyZeroInteractions(authenticationService);
    }

    @Test
    public void genreFindIfIdSpecified() {

        final int givenId = 1;
        final Genre genre = buildGenre(givenId);
        when(genreService.getById(anyInt())).thenReturn(genre);

        String actualOutput = appCommands.genreFind(givenId, defaultString);

        assertEquals(genre.toString(), actualOutput);
        verify(genreService).getById(integerArgumentCaptor.capture());
        assertEquals(givenId, integerArgumentCaptor.getValue(), 0);

        verifyNoMoreInteractions(genreService);
        verifyZeroInteractions(bookService);
        verifyZeroInteractions(authorService);
        verifyZeroInteractions(commentService);
        verifyZeroInteractions(authenticationService);
    }

    @Test
    public void genreFindIfNameSpecified() {

        final int givenId = 1;
        final String givenName = "genre_name";
        final Genre genre = buildGenre(givenId, givenName);
        when(genreService.getByName(anyString())).thenReturn(genre);

        String actualOutput = appCommands.genreFind(defaultInt, givenName);

        assertEquals(genre.toString(), actualOutput);
        verify(genreService).getByName(stringArgumentCaptor.capture());
        assertEquals(givenName, stringArgumentCaptor.getValue());

        verifyNoMoreInteractions(genreService);
        verifyZeroInteractions(bookService);
        verifyZeroInteractions(authorService);
        verifyZeroInteractions(commentService);
        verifyZeroInteractions(authenticationService);
    }

    @Test
    public void genreFindIfNameAndIdNotSpecified() {

        final List<Genre> genres = buildGenreList(3);
        when(genreService.getAll()).thenReturn(genres);

        String actualOutput = appCommands.genreFind(defaultInt, defaultString);

        assertEquals(genres.toString(), actualOutput);
        verify(genreService).getAll();

        verifyNoMoreInteractions(genreService);
        verifyZeroInteractions(bookService);
        verifyZeroInteractions(authorService);
        verifyZeroInteractions(commentService);
        verifyZeroInteractions(authenticationService);
    }

    @Test
    public void genreDelete() {

        final int givenId = 1;
        final String expectOutput = "deleted";

        String actualOutput = appCommands.genreDelete(givenId);

        assertEquals(expectOutput, actualOutput);
        verify(genreService).deleteById(integerArgumentCaptor.capture());
        assertEquals(givenId, integerArgumentCaptor.getValue(), 0);

        verifyNoMoreInteractions(genreService);
        verifyZeroInteractions(bookService);
        verifyZeroInteractions(authorService);
        verifyZeroInteractions(commentService);
        verifyZeroInteractions(authenticationService);
    }

    @Test
    public void genreUpdate() {

        final int givenId = 1;
        final String givenGenreName = "test_genreName";

        Genre genre = buildGenre(givenId, givenGenreName);

        final String expectOutput = "updated: " + genre.toString();

        when(genreService.updateById(anyInt(), any(Genre.class))).thenReturn(genre);

        String actualOutput = appCommands.genreUpdate(givenId, givenGenreName);

        assertEquals(expectOutput, actualOutput);
        verify(genreService).updateById(integerArgumentCaptor.capture(), genreArgumentCaptor.capture());

        assertEquals(givenId, integerArgumentCaptor.getValue(), 0);
        assertEquals(new Genre(givenGenreName), genreArgumentCaptor.getValue());

        verifyNoMoreInteractions(genreService);
        verifyZeroInteractions(bookService);
        verifyZeroInteractions(authorService);
        verifyZeroInteractions(commentService);
        verifyZeroInteractions(authenticationService);
    }

    @Test
    public void genreSave() {

        final int givenId = 1;
        final String givenGenreName = "test_genreName";

        Genre genre = buildGenre(givenId, givenGenreName);

        final String expectOutput = "saved: " + genre.toString();

        when(genreService.save(any(Genre.class))).thenReturn(genre);

        String actualOutput = appCommands.genreSave(givenGenreName);

        assertEquals(expectOutput, actualOutput);
        verify(genreService).save(genreArgumentCaptor.capture());

        assertEquals(new Genre(givenGenreName), genreArgumentCaptor.getValue());

        verifyNoMoreInteractions(genreService);
        verifyZeroInteractions(bookService);
        verifyZeroInteractions(authorService);
        verifyZeroInteractions(commentService);
        verifyZeroInteractions(authenticationService);
    }

    @Test
    public void bookFindIfIdSpecified() {

        final int givenId = 1;
        final Book book = buildBook(givenId);
        when(bookService.getById(anyInt())).thenReturn(book);

        String actualOutput = appCommands.bookFind(givenId, defaultString);

        assertEquals(book.toString(), actualOutput);
        verify(bookService).getById(integerArgumentCaptor.capture());
        assertEquals(givenId, integerArgumentCaptor.getValue(), 0);

        verifyNoMoreInteractions(bookService);
        verifyZeroInteractions(genreService);
        verifyZeroInteractions(authorService);
        verifyZeroInteractions(commentService);
        verifyZeroInteractions(authenticationService);
    }

    @Test
    public void bookFindIfNameSpecified() {

        final int givenId = 1;
        final String givenName = "book_name";
        final Book book = buildBook(givenId, givenName);
        when(bookService.getByName(anyString())).thenReturn(book);

        String actualOutput = appCommands.bookFind(defaultInt, givenName);

        assertEquals(book.toString(), actualOutput);
        verify(bookService).getByName(stringArgumentCaptor.capture());
        assertEquals(givenName, stringArgumentCaptor.getValue());

        verifyNoMoreInteractions(bookService);
        verifyZeroInteractions(genreService);
        verifyZeroInteractions(authorService);
        verifyZeroInteractions(commentService);
        verifyZeroInteractions(authenticationService);
    }

    @Test
    public void bookFindIfNameAndIdNotSpecified() {

        final List<Book> books = buildBookList(3);
        when(bookService.getAll()).thenReturn(books);

        String actualOutput = appCommands.bookFind(defaultInt, defaultString);

        assertEquals(books.toString(), actualOutput);
        verify(bookService).getAll();

        verifyNoMoreInteractions(bookService);
        verifyZeroInteractions(genreService);
        verifyZeroInteractions(authorService);
        verifyZeroInteractions(commentService);
        verifyZeroInteractions(authenticationService);
    }

    private List<Book> buildBookList(int count){

        List<Book> books = new ArrayList<>(count);

        for (int i = 0; i < count; i++) {
            books.add(buildBook(i, "test_name" + i));
        }
        return books;
    }

    private Book buildBook(int bookId, String bookName){

        Book book = new Book(bookName, buildGenre(5), buildAuthorList(2));
        book.setId(bookId);

        return book;
    }

    private Book buildBook(int bookId){

        Book book = new Book("book_name", buildGenre(5), buildAuthorList(2));
        book.setId(bookId);

        return book;
    }

    private List<Genre> buildGenreList(int count){

        List<Genre> genres = new ArrayList<>(count);

        for (int i = 0; i < count; i++) {
            genres.add(buildGenre(i, "test_name" + i));
        }
        return genres;
    }

    private Genre buildGenre(int id, String genreName){

        Genre genre = new Genre(genreName);
        genre.setId(id);
        return genre;
    }

    private Genre buildGenre(int id){

        Genre genre = new Genre("genre_name");
        genre.setId(id);
        return genre;
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

        List<Author> authors = new ArrayList<>(count);

        for (int i = 0; i < count; i++) {
            authors.add(buildAuthor(i, "test_name" + i));
        }
        return authors;
    }
}