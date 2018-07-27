package my.hhorushko.otus.library.dao;

import my.hhorushko.otus.library.domain.Book;

import java.util.List;

public interface BookDao {
    Book findBookById(int id);

    List<Book> findAll();

    Book findByName(String name);

    Book insert(Book book);

    void delete(int id);
}
