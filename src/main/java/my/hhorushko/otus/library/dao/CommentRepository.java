package my.hhorushko.otus.library.dao;

import my.hhorushko.otus.library.domain.Comment;

public interface CommentRepository {

    void save(Comment comment);
}
