package my.hhorushko.otus.library.dao;

import my.hhorushko.otus.library.domain.Genre;

import java.util.List;

public interface GenreDao {
    
    Genre findByName(String name);

    Genre findById(int id);

    void deleteById(int id);

    Genre update(Genre genre);

    List<Genre> findAll();

    Genre insert(Genre genre);
}
