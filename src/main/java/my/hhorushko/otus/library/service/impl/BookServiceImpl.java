package my.hhorushko.otus.library.service.impl;

import lombok.AllArgsConstructor;
import my.hhorushko.otus.library.dao.BookRepository;
import my.hhorushko.otus.library.domain.Author;
import my.hhorushko.otus.library.domain.Book;
import my.hhorushko.otus.library.domain.User;
import my.hhorushko.otus.library.exception.LibraryNotFoundException;
import my.hhorushko.otus.library.service.AuthorService;
import my.hhorushko.otus.library.service.BookService;
import my.hhorushko.otus.library.service.GenreService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class BookServiceImpl implements BookService {

    private AuthorService authorService;
    private GenreService genreService;

    private BookRepository bookRepository;

    @Override
    public Book getById(int id){

        Book book = getBook(bookRepository.findById(id), String.format("Book with id: %s not found"));
        return book;
    }

    @Override
    public List<Book> getAll(){

        return bookRepository.findAll();
    }

    @Override
    public Book getByName(String name){

        Book book = getBook(bookRepository.findByName(name), String.format("Book with id: %s not found"));

        return book;
    }

    public Book save(Book book){
        return bookRepository.save(book);
    }

    @Override
    public Book updateById(int id, Book book) {
        return null;
    }

    @Override
    public Book save(String bookName, int genreId, int[] authorIds){

        List<Author> authors = new ArrayList<>(authorIds.length);
        for (int authorId : authorIds) {
            authors.add(authorService.getById(authorId));
        }
        Book book = new Book(bookName, genreService.getById(genreId), authors);
        return bookRepository.save(book);
    }

    @Override
    public void deleteById(int id) {

        getById(id);
        bookRepository.deleteById(id);
    }

    private Book getBook(Optional<Book> optionalBook, String errorMessage){

        if(!optionalBook.isPresent()){
            throw new LibraryNotFoundException(errorMessage);
        }

        return optionalBook.get();
    }
}
