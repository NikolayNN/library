package my.hhorushko.otus.library.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

@Data
@Entity
public class Genre {

    @Id
    @GeneratedValue
    private int id;

    @NotBlank
    @Column(unique = true, nullable = false)
    private String name;

    public Genre() {
    }

    public Genre(String name) {
        this.name = name;
    }
}
