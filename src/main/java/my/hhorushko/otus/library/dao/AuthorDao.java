package my.hhorushko.otus.library.dao;

import my.hhorushko.otus.library.domain.Author;

import java.util.List;

public interface AuthorDao {
    Author findByName(String name);

    Author findById(int id);

    void deleteById(int id);

    Author update(Author author);

    List<Author> findAll();

    Author insert(Author author);

    List<Author> findAuthorByBookId(int id);
}
