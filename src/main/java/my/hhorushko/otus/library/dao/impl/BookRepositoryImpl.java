package my.hhorushko.otus.library.dao;

import lombok.AllArgsConstructor;
import my.hhorushko.otus.library.domain.Book;
import my.hhorushko.otus.library.domain.Genre;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
@AllArgsConstructor
public class BookDaoImpl implements BookDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Book findBookById(int id){
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
    public void delete(int id) {

        Book book = findBookById(id);
        em.remove(book);
    }
}
