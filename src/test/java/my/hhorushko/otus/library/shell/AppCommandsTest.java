//package my.hhorushko.otus.library.shell;
//
//import my.hhorushko.otus.library.domain.Author;
//import my.hhorushko.otus.library.service.AuthorService;
//import my.hhorushko.otus.library.service.BookService;
//import my.hhorushko.otus.library.service.GenreService;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.ArgumentCaptor;
//import org.mockito.Captor;
//import org.mockito.Mock;
//import org.mockito.junit.MockitoJUnitRunner;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import static org.junit.Assert.*;
//import static org.mockito.ArgumentMatchers.anyInt;
//import static org.mockito.ArgumentMatchers.anyString;
//import static org.mockito.Mockito.*;
//
//@RunWith(MockitoJUnitRunner.class)
//public class AppCommandsTest {
//
//    @Mock
//    private AuthorService authorService;
//    @Mock
//    private GenreService genreService;
//    @Mock
//    private BookService bookService;
//
//    @Captor
//    private ArgumentCaptor<Integer> integerArgumentCaptor;
//    @Captor
//    private ArgumentCaptor<String> stringArgumentCaptor;
//
//    private AppCommands appCommands;
//
//    private final int defaultInt = -1;
//    private final String defaultString = "";
//
//
//    @Before
//    public void setUp() throws Exception {
//        appCommands = new AppCommands(authorService, genreService, bookService);
//    }
//
//    @Test
//    public void authorFindIfIdSpecified() {
//
//        final int givenId = 1;
//        final Author expectAuthor = buildAuthor(givenId);
//        when(authorService.getById(anyInt())).thenReturn(expectAuthor);
//
//        String actualOutput = appCommands.authorFind(givenId, defaultString);
//
//        assertEquals(expectAuthor.toString(), actualOutput);
//        verify(authorService).getById(integerArgumentCaptor.capture());
//        assertEquals(givenId, integerArgumentCaptor.getValue(), 0);
//        verifyNoMoreInteractions(authorService);
//        verifyZeroInteractions(bookService);
//        verifyZeroInteractions(genreService);
//    }
//
//    @Test
//    public void authorFindIfNameSpecified() {
//
//        final int givenId = 1;
//        final String givenName = "test_name";
//        final Author expectAuthor = buildAuthor(givenId, givenName);
//        when(authorService.getByName(anyString())).thenReturn(expectAuthor);
//
//        String actualOutput = appCommands.authorFind(defaultInt, givenName);
//
//        assertEquals(expectAuthor.toString(), actualOutput);
//        verify(authorService).getByName(stringArgumentCaptor.capture());
//        assertEquals(givenName, stringArgumentCaptor.getValue());
//        verifyNoMoreInteractions(authorService);
//        verifyZeroInteractions(bookService);
//        verifyZeroInteractions(genreService);
//    }
//
//    @Test
//    public void authorFindIfNameAndIdNotSpecified() {
//
//        final List<Author> expectAuthors = buildAuthorList(3);
//        when(authorService.getAll()).thenReturn(expectAuthors);
//
//        String actualOutput = appCommands.authorFind(defaultInt, defaultString);
//
//        assertEquals(expectAuthors.toString(), actualOutput);
//        verify(authorService, times(1)).getAll();
//
//        verifyNoMoreInteractions(authorService);
//        verifyZeroInteractions(bookService);
//        verifyZeroInteractions(genreService);
//    }
//
//    @Test
//    public void authorDelete() {
//
//        final int givenId = 1;
//        final String expectOutput = "deleted";
//
//        String actualOutput = appCommands.authorDelete(givenId);
//
//        assertEquals(expectOutput, actualOutput);
//        verify(authorService).deleteById(integerArgumentCaptor.capture());
//        assertEquals(givenId, integerArgumentCaptor.getValue(), 0);
//        verifyNoMoreInteractions(authorService);
//        verifyZeroInteractions(bookService);
//        verifyZeroInteractions(genreService);
//    }
//
//    @Test
//    public void authorUpdate() {
//
//    }
//
//    @Test
//    public void authorSave() {
//    }
//
//    @Test
//    public void genreFind() {
//    }
//
//    @Test
//    public void genreDelete() {
//    }
//
//    @Test
//    public void genreUpdate() {
//    }
//
//    @Test
//    public void genreSave() {
//    }
//
//    @Test
//    public void bookFind() {
//    }
//
//    @Test
//    public void bookSave() {
//    }
//
//    @Test
//    public void bookDelete() {
//    }
//
//    private Author buildAuthor(int id) {
//
//        Author author = new Author();
//        author.setId(id);
//        author.setName("test_name");
//        return author;
//    }
//
//    private Author buildAuthor(int id, String name) {
//
//        Author author = new Author();
//        author.setId(id);
//        author.setName(name);
//        return author;
//    }
//
//    private List<Author> buildAuthorList(int count) {
//
//        List<Author> authors = new ArrayList<>(count);
//
//        for (int i = 0; i < count; i++) {
//            authors.add(buildAuthor(i, "test_name" + i));
//        }
//        return authors;
//    }
//}