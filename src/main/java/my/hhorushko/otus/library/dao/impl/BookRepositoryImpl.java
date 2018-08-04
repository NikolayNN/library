package my.hhorushko.otus.library.dao.impl;

import lombok.AllArgsConstructor;
import my.hhorushko.otus.library.dao.BookRepository;
import my.hhorushko.otus.library.domain.Book;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
@AllArgsConstructor
public class BookRepositoryImpl implements BookRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Book findById(int id){
        return em.find(Book.class, id);
    }

    @Override
    public List<Book> findAll(){

        TypedQuery<Book> query = em.createQuery("SELECT b FROM Book b", Book.class);

        return query.getResultList();
    }

    @Override
    public Book findByName(String name){

        TypedQuery<Book> query = em.createQuery("SELECT b FROM Book b WHERE b.name=:name", Book.class);
        return query.getSingleResult();
    }

    @Override
    public Book insert(Book book){

        em.persist(book);
        return book;
    }

    @Override
    public void deleteById(int id) {

        Book book = findById(id);
        em.remove(book);
    }

    @Override
    public Book update(Book book) {

        em.merge(book);
        return book;
    }
}
