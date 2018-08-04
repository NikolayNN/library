package my.hhorushko.otus.library.service.impl;

import lombok.AllArgsConstructor;
import my.hhorushko.otus.library.dao.GenreRepository;
import my.hhorushko.otus.library.domain.Genre;
import my.hhorushko.otus.library.service.GenreService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class GenreServiceImpl implements GenreService {

    private final GenreRepository genreRepository;

    @Override
    public Genre getById(int id){
        return genreRepository.findById(id);
    }

    @Override
    public Genre getByName(String name){
        return genreRepository.findByName(name);
    }

    @Override
    public void deleteById(int id){
        genreRepository.deleteById(id);
    }

    @Override
    public Genre updateById(int id, Genre genre){
        genre.setId(id);
        return genreRepository.update(genre);
    }

    @Override
    public Genre save(Genre genre) {
        return genreRepository.insert(genre);
    }

    @Override
    public List<Genre> getAll() {
        return genreRepository.findAll();
    }
}
