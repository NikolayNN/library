package my.hhorushko.otus.library.dao.impl;

import lombok.AllArgsConstructor;
import my.hhorushko.otus.library.dao.UserRepository;
import my.hhorushko.otus.library.domain.User;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
@Transactional
@AllArgsConstructor
public class UserRepositoryImpl implements UserRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<User> findAll(){

        TypedQuery<User> query = em.createQuery("SELECT u FROM User u", User.class);

        return query.getResultList();
    }

    @Override
    public User findById(int id){

        return em.find(User.class, id);
    }

    @Override
    public User findByName(String name){

        TypedQuery<User> query = em.createQuery("SELECT u FROM User u WHERE u.name=:name", User.class);
        query.setParameter("name", name);
        return query.getSingleResult();
    }

    @Override
    public User insert(User user){

        em.persist(user);
        return user;
    }

    @Override
    public User update(User user){

        em.merge(user);
        return user;
    }

    @Override
    public void deleteById(int id){

        User user = findById(id);
        em.remove(user);
    }
}
