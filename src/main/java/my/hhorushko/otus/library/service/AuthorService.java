package my.hhorushko.otus.library.service;

import my.hhorushko.otus.library.domain.Author;

import java.util.List;

public interface AuthorService {
    Author findById(int id);

    List<Author> findById(int[] ids);

    Author findByName(String name);

    void deleteById(int id);

    Author updateById(int id, Author author);

    Author save(Author author);

    List<Author> getAll();
}
