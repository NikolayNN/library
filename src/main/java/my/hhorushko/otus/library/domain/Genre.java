package my.hhorushko.otus.library.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity
public class Genre {

    @Id
    @GeneratedValue
    private int id;
    private String name;

    public Genre() {
    }

    public Genre(String name) {
        this.name = name;
    }
}
