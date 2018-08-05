package my.hhorushko.otus.library.service;

import lombok.AllArgsConstructor;
import my.hhorushko.otus.library.dao.GenreDao;
import my.hhorushko.otus.library.domain.Genre;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class GenreServiceImpl implements GenreService {

    private final GenreDao genreDao;

    @Override
    public Genre findById(int id){
        return genreDao.findById(id);
    }

    @Override
    public Genre findByName(String name){
        return genreDao.findByName(name);
    }

    @Override
    public void deleteById(int id){
        genreDao.deleteById(id);
    }

    @Override
    public Genre updateById(int id, Genre genre){
        genre.setId(id);
        return genreDao.update(genre);
    }

    @Override
    public Genre save(Genre genre) {
        return genreDao.insert(genre);
    }

    @Override
    public List<Genre> getAll() {
        return genreDao.findAll();
    }
}
