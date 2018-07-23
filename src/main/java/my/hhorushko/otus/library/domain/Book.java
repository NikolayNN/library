package my.hhorushko.otus.library.domain;

import lombok.Data;

import java.util.List;

@Data
public class Book {

    private int id;
    private String name;
    private Genre genre;
    private List<Author> authors;

    public Book() {
    }

    public Book(String name, Genre genre, List<Author> authors) {
        this.name = name;
        this.genre = genre;
        this.authors = authors;
    }
}
