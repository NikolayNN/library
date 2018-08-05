package my.hhorushko.otus.library.domain;

import lombok.Data;
import org.aspectj.lang.annotation.DeclareAnnotation;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@Entity
public class Comment {

    public Comment(String text, Book book, User user) {
        this.text = text;
        this.book = book;
        this.user = user;
    }

    @Id
    @GeneratedValue
    private int id;
    private String text;

    @NotNull
    @ManyToOne
    private Book book;

    @NotNull
    @OneToOne
    private User user;
}
