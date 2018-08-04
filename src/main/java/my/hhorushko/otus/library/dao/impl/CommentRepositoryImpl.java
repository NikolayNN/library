package my.hhorushko.otus.library.dao.impl;

import lombok.AllArgsConstructor;
import my.hhorushko.otus.library.dao.CommentRepository;
import my.hhorushko.otus.library.domain.Comment;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
@Transactional
@AllArgsConstructor
public class CommentRepositoryImpl implements CommentRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void save(Comment comment) {
        em.persist(comment);
    }
}
