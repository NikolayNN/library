package my.hhorushko.otus.library.dao;

import my.hhorushko.otus.library.domain.Author;

import java.util.List;

public interface AuthorRepository {
    Author findByName(String name);

    Author findById(int id);

    List<Author> findById(int[] ids);

    void deleteById(int id);

    Author update(Author author);

    List<Author> findAll();

    Author insert(Author author);

    List<Author> findAuthorByBookId(int id);
}
