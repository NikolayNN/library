package my.hhorushko.otus.library.service;

import lombok.AllArgsConstructor;
import my.hhorushko.otus.library.dao.BookDao;
import my.hhorushko.otus.library.domain.Book;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class BookServiceImpl implements BookService {

    private BookDao bookDao;

    @Override
    public Book getBookById(int id){
        return bookDao.findBookById(id);
    }

    @Override
    public List<Book> getBookList(){
        return bookDao.findAll();
    }

    @Override
    public Book getBookByName(String name){
        return bookDao.findByName(name);
    }

    @Override
    public Book saveBook(String bookName, int genreId, int[] authorIds){
        return bookDao.insert(bookName, genreId, authorIds);
    }

    @Override
    public void deleteBook(int id) {
        bookDao.delete(id);
    }
}
