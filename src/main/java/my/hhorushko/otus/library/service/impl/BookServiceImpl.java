package my.hhorushko.otus.library.service.impl;

import lombok.AllArgsConstructor;
import my.hhorushko.otus.library.dao.BookRepository;
import my.hhorushko.otus.library.domain.Book;
import my.hhorushko.otus.library.service.AuthorService;
import my.hhorushko.otus.library.service.BookService;
import my.hhorushko.otus.library.service.GenreService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class BookServiceImpl implements BookService {

    private AuthorService authorService;
    private GenreService genreService;

    private BookRepository bookRepository;

    @Override
    public Book getById(int id){

        return bookRepository.findById(id);
    }

    @Override
    public List<Book> getAll(){

        return bookRepository.findAll();
    }

    @Override
    public Book getByName(String name){

        return bookRepository.findByName(name);
    }

    public Book save(Book book){
        return bookRepository.insert(book);
    }

    @Override
    public Book updateById(int id, Book book) {
        return null;
    }

    @Override
    public Book save(String bookName, int genreId, int[] authorIds){

        Book book = new Book(bookName, genreService.getById(genreId),
                authorService.findById(authorIds));
        return bookRepository.insert(book);
    }

    @Override
    public void deleteById(int id) {
        bookRepository.deleteById(id);
    }
}
