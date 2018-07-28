package my.hhorushko.otus.library.dao;

import lombok.AllArgsConstructor;
import my.hhorushko.otus.library.domain.Author;
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
public class GenreDaoImpl implements GenreDao{


    @PersistenceContext
    private EntityManager em;

    @Override
    public Genre findByName(String name){

        TypedQuery<Genre> query = em.createQuery("SELECT g FROM Genre g WHERE g.name=:name", Genre.class);
        query.setParameter("name", name);
        return query.getSingleResult();
    }

    @Override
    public Genre findById(int id){
        return em.find(Genre.class, id);
    }

    @Override
    public void deleteById(int id){

       Genre genre = findById(id);
       em.remove(genre);
    }

    @Override
    public Genre update(Genre genre){

        em.merge(genre);
        return genre;
    }

    @Override
    public List<Genre> findAll(){

        TypedQuery<Genre> query = em.createQuery("SELECT g FROM Genre g", Genre.class);

        return query.getResultList();
    }

    @Override
    public Genre insert(Genre genre){

        em.persist(genre);
        return genre;
    }
}
