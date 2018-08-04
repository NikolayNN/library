package my.hhorushko.otus.library.service;

import java.util.List;

public interface  BaseCrudService<T> {

    List<T> getAll();

    T getById(int id);

    T getByName(String name);

    T save(T t);

    T updateById(int id, T t);

    void deleteById(int id);
}
