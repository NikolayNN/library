package my.hhorushko.otus.library.service.impl;

import lombok.AllArgsConstructor;
import my.hhorushko.otus.library.dao.GenreRepository;
import my.hhorushko.otus.library.domain.Genre;
import my.hhorushko.otus.library.exception.LibraryNotFoundException;
import my.hhorushko.otus.library.service.GenreService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class GenreServiceImpl implements GenreService {

    private final GenreRepository genreRepository;

    @Override
    public Genre getById(int id){

        Genre genre = getGenre(genreRepository.findById(id), String.format("Genre with id: %s not found", id));
        return genre;
    }

    @Override
    public Genre getByName(String name){

        Genre genre = getGenre(genreRepository.findByName(name), String.format("Genre with name: %s not found", name));
        return genre;
    }

    @Override
    public void deleteById(int id){
        getById(id);
        genreRepository.deleteById(id);
    }

    @Override
    public Genre updateById(int id, Genre genre){
        genre.setId(id);
        return genreRepository.save(genre);
    }

    @Override
    public Genre save(Genre genre) {
        return genreRepository.save(genre);
    }

    @Override
    public List<Genre> getAll() {
        return genreRepository.findAll();
    }

    private Genre getGenre(Optional<Genre> genreOptional, String errorMessage){

        if(!genreOptional.isPresent()){
            throw new LibraryNotFoundException(errorMessage);
        }

        return genreOptional.get();
    }
}
