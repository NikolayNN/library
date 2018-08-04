package my.hhorushko.otus.library.service.impl;

import lombok.AllArgsConstructor;
import my.hhorushko.otus.library.dao.CommentRepository;
import my.hhorushko.otus.library.domain.Book;
import my.hhorushko.otus.library.domain.Comment;
import my.hhorushko.otus.library.domain.User;
import my.hhorushko.otus.library.service.AuthenticaticalService;
import my.hhorushko.otus.library.service.BookService;
import my.hhorushko.otus.library.service.CommentService;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CommentServiceImpl implements CommentService {

    private CommentRepository commentRepository;
    private AuthenticaticalService authenticaticalService;
    private BookService bookService;

    @Override
    public void addComment(int bookId, String text){
        Book book = bookService.getById(bookId);
        User user = authenticaticalService.getCurrentUser();
        Comment comment = new Comment(text, book, user);
        commentRepository.save(comment);
    }
}
