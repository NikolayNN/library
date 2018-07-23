package my.hhorushko.otus.library.service;

import lombok.AllArgsConstructor;
import my.hhorushko.otus.library.dao.AuthorDao;
import my.hhorushko.otus.library.dao.AuthorDaoImpl;
import my.hhorushko.otus.library.domain.Author;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class AuthorServiceImpl implements AuthorService {

    private final AuthorDao authorDao;

    @Override
    public Author findById(int id){
        return authorDao.findById(id);
    }

    @Override
    public Author findByName(String name){
        return authorDao.findByName(name);
    }

    @Override
    public void deleteById(int id){
        authorDao.deleteById(id);
    }

    @Override
    public Author updateById(int id, Author author){
        author.setId(id);
        return authorDao.update(author);
    }

    @Override
    public Author save(Author author) {
        return authorDao.insert(author);
    }

    @Override
    public List<Author> getAll() {
        return authorDao.findAll();
    }
}
