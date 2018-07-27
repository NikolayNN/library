package my.hhorushko.otus.library.service;

import lombok.AllArgsConstructor;
import my.hhorushko.otus.library.dao.AuthorRepository;
import my.hhorushko.otus.library.domain.Author;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;

    @Override
    public Author findById(int id){
        return authorRepository.findById(id);
    }

    @Override
    public List<Author> findById(int[] ids){
        return authorRepository.findById(ids);
    }

    @Override
    public Author findByName(String name){
        return authorRepository.findByName(name);
    }

    @Override
    public void deleteById(int id){
        authorRepository.deleteById(id);
    }

    @Override
    public Author updateById(int id, Author author){
        author.setId(id);
        return authorRepository.update(author);
    }

    @Override
    public Author save(Author author) {
        return authorRepository.insert(author);
    }

    @Override
    public List<Author> getAll() {
        return authorRepository.findAll();
    }
}
