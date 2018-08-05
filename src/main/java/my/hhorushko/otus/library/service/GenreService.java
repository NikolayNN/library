package my.hhorushko.otus.library.service;

import my.hhorushko.otus.library.domain.Genre;

import java.util.List;

public interface GenreService {
    Genre findById(int id);

    Genre findByName(String name);

    void deleteById(int id);

    Genre updateById(int id, Genre genre);

    Genre save(Genre genre);

    List<Genre> getAll();
}
