package my.hhorushko.otus.library.service;

import my.hhorushko.otus.library.domain.Book;

import java.util.List;

public interface BookService {
    Book getBookById(int id);

    List<Book> getBookList();

    Book getBookByName(String name);

    Book saveBook(String bookName, int genreId, int[] authorIds);

    void deleteBook(int id);
}
