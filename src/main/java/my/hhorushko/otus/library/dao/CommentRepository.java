package my.hhorushko.otus.library.dao;

import my.hhorushko.otus.library.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Integer> {
}
