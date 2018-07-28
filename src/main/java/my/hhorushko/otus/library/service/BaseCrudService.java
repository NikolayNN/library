package my.hhorushko.otus.library.service.impl;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import my.hhorushko.otus.library.dao.BaseCrudRepository;
import my.hhorushko.otus.library.dao.GenreRepository;
import my.hhorushko.otus.library.domain.Genre;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Service
public abstract class BaseCrudService<T> {

    private final BaseCrudRepository baseCrudRepository;

    public T findById(int id){
        return (T) baseCrudRepository.findById(id);
    }

    public T findByName(String name){
        return (T) baseCrudRepository.findByName(name);
    }

    public void deleteById(int id){
        baseCrudRepository.deleteById(id);
    }

    public T save(T t) {
        return (T) baseCrudRepository.insert(t);
    }

    public List<T> getAll() {
        return baseCrudRepository.findAll();
    }
}
