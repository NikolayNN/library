package my.hhorushko.otus.library.domain;

import lombok.Data;

@Data
public class Genre {

    private int id;
    private String name;

    public Genre() {
    }

    public Genre(String name) {
        this.name = name;
    }
}
