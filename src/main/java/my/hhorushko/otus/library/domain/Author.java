package my.hhorushko.otus.library.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity
public class Author {

    @Id
    @GeneratedValue
    private int id;

    @Column(unique = true)
    private String name;

    public Author() {
    }

    public Author(String name) {
        this.name = name;
    }
}
