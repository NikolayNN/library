package my.hhorushko.otus.library.service;

import my.hhorushko.otus.library.domain.Book;

import java.util.List;

public interface BookService extends BaseCrudService<Book> {
    Book save(String bookName, int genreId, int[] authorIds);
}
