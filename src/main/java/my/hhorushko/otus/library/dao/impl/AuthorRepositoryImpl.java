package my.hhorushko.otus.library.dao.impl;

import lombok.AllArgsConstructor;
import my.hhorushko.otus.library.dao.AuthorRepository;
import my.hhorushko.otus.library.domain.Author;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

@Repository
@SuppressWarnings("JpaQlInspection")
@AllArgsConstructor
@Transactional
public class AuthorRepositoryImpl implements AuthorRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Author findByName(String name){

        TypedQuery<Author> query = em.createQuery("Select a FROM Author a WHERE a.name = :name", Author.class);
        query.setParameter("name", name);
        return query.getSingleResult();
    }

    @Override
    public Author findById(int id){

        return em.find(Author.class, id);
    }

    @Override
    public List<Author> findById(int[] ids){

        List<Author> result = new ArrayList<>();
        for (int id : ids) {
            result.add(em.find(Author.class, id));
        }
        return result;
    }

    @Override
    public void deleteById(int id){

        Author author = findById(id);
        em.remove(author);
    }

    @Override
    public Author update(Author author){

        em.merge(author);
        return author;
    }

    @Override
    public List<Author> findAll(){

        return em.createQuery("SELECT a FROM Author a").getResultList();
    }

    @Override
    public Author insert(Author author){

        em.persist(author);
        return author;
    }

    @Override
    public List<Author> findAuthorByBookId(int id){

        return null;
    }
}
