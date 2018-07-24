package my.hhorushko.otus.library.domain;

import lombok.Data;

@Data
public class Author {

    private int id;
    private String name;

    public Author() {
    }

    public Author(String name) {
        this.name = name;
    }
}
