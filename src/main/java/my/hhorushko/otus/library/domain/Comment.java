package my.hhorushko.otus.library.domain;

import lombok.Data;
import org.aspectj.lang.annotation.DeclareAnnotation;

import javax.persistence.*;

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

    @ManyToOne
    private Book book;
    @OneToOne
    private User user;
}
