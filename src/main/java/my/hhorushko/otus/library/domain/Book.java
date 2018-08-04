package my.hhorushko.otus.library.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class Book {

    @Id
    @GeneratedValue
    private int id;

    @Column(unique = true)
    private String name;
    @OneToOne
    private Genre genre;
    @ManyToMany
    @JoinTable(
            name="book_author",
            joinColumns = {@JoinColumn(name = "book_id")},
            inverseJoinColumns = {@JoinColumn(name = "author_id")}
    )
    private List<Author> authors;

    @OneToMany(mappedBy = "book")
    private List<Comment> comments;

    public Book() {
    }

    public Book(String name, Genre genre, List<Author> authors) {
        this.name = name;
        this.genre = genre;
        this.authors = authors;
    }
}
