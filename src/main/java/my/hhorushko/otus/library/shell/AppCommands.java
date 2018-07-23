package my.hhorushko.otus.library.shell;

import lombok.AllArgsConstructor;
import my.hhorushko.otus.library.domain.Author;
import my.hhorushko.otus.library.domain.Genre;
import my.hhorushko.otus.library.service.AuthorService;
import my.hhorushko.otus.library.service.BookService;
import my.hhorushko.otus.library.service.GenreService;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

@ShellComponent
@AllArgsConstructor
public class AppCommands {

    private final AuthorService authorService;
    private final GenreService genreService;
    private final BookService bookService;

    @ShellMethod("find author with id")
    public String authorFind(@ShellOption(value = {"-I", "--id"}, defaultValue = "-1") Integer id,
                             @ShellOption(value = {"-N", "--name"}, defaultValue = "") String name) {

        if (id != -1) {
            return authorService.findById(id).toString();
        }
        if (!name.isEmpty()) {
            return authorService.findByName(name).toString();
        }
        return authorService.getAll().toString();
    }

    @ShellMethod("delete author with id")
    public String authorDelete(@ShellOption Integer id) {

        authorService.deleteById(id);
        return "deleted";
    }


    @ShellMethod("update author with id")
    public String authorUpdate(@ShellOption(value = {"-I", "--id"}) Integer id,
                               @ShellOption(value = {"-N", "--name"}) String name) {

        return "updated: " + authorService.updateById(id, new Author(name));
    }

    @ShellMethod("save new Author")
    public String authorSave(@ShellOption(value = {"-N", "--name"}) String name) {
        Author author = authorService.save(new Author(name));
        return "saved: " + author;
    }

    @ShellMethod("find genre with id")
    public String genreFind(@ShellOption(value = {"-I", "--id"}, defaultValue = "-1") Integer id,
                            @ShellOption(value = {"-N", "--name"}, defaultValue = "") String name) {

        if (id != -1) {
            return genreService.findById(id).toString();
        }
        if (!name.isEmpty()) {
            return genreService.findByName(name).toString();
        }
        return genreService.getAll().toString();
    }

    @ShellMethod("delete genre with id")
    public String genreDelete(@ShellOption(value = {"-I", "--id"}) Integer id) {

        genreService.deleteById(id);
        return "deleted";
    }


    @ShellMethod("update genre with id")
    public String genreUpdate(@ShellOption(value = {"-I", "--id"}) Integer id,
                              @ShellOption(value = {"-N", "--name"}) String name) {

        return "updated: " + genreService.updateById(id, new Genre(name));
    }

    @ShellMethod("save new Genre")
    public String genreSave(@ShellOption String name) {

        Genre genre = genreService.save(new Genre(name));
        return "saved: " + genre;
    }

    @ShellMethod("find book")
    public String bookFind(@ShellOption(value = {"-I", "--id"}, defaultValue = "-1") Integer id,
                           @ShellOption(value = {"-N", "--name"}, defaultValue = "") String name) {
        if (id != -1) {
            return bookService.getBookById(id).toString();
        }
        if (!name.isEmpty()) {
            return bookService.getBookByName(name).toString();
        }
        return bookService.getBookList().toString();
    }



    @ShellMethod("save book")
    public String bookSave(
            @ShellOption({"-N", "--name"}) String name,
            @ShellOption({"-GI", "--genre-id"}) int genreId,
            @ShellOption({"-AI", "--author-id"}) String authorId) {

        String[] authorStrings = authorId.split(",");

        int[] authors = new int[authorStrings.length];

        for (int i = 0; i < authors.length; i++) {

            authors[i] = Integer.valueOf(authorStrings[i]);
        }

        return bookService.saveBook(name, genreId, authors).toString();
    }

    @ShellMethod("delete book")
    public String bookDelete(
            @ShellOption({"-I", "--id"}) int id){

        bookService.deleteBook(id);
        return "deleted";
    }
}
