package my.hhorushko.otus.library.dao;

import java.util.List;

public interface BaseCrudRepository<T> {

    T findByName(String name);

    T findById(int id);

    void deleteById(int id);

    T update(T t);

    List<T> findAll();

    T insert(T t);
}
