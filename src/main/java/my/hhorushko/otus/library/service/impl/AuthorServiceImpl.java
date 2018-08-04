package my.hhorushko.otus.library.service.impl;

import lombok.AllArgsConstructor;
import my.hhorushko.otus.library.dao.AuthorRepository;
import my.hhorushko.otus.library.domain.Author;
import my.hhorushko.otus.library.domain.User;
import my.hhorushko.otus.library.exception.LibraryNotFoundException;
import my.hhorushko.otus.library.service.AuthorService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;

    @Override
    public Author getById(int id){

        Author author = getAuthor(authorRepository.findById(id), String.format("Author with id: %s not found", id));
        return author;
    }

    @Override
    public Author getByName(String name){
        Author author = getAuthor(authorRepository.findByName(name), String.format("Author with name: %s not found", name));
        return author;
    }

    @Override
    public void deleteById(int id){
        getById(id);
        authorRepository.deleteById(id);
    }

    @Override
    public Author updateById(int id, Author author){
        getById(id);
        author.setId(id);
        return authorRepository.save(author);
    }

    @Override
    public Author save(Author author) {
        return authorRepository.save(author);
    }

    @Override
    public List<Author> getAll() {
        return authorRepository.findAll();
    }

    private Author getAuthor(Optional<Author> optionalAuthor, String errorMessage){

        if(!optionalAuthor.isPresent()){
            throw new LibraryNotFoundException(errorMessage);
        }

        return optionalAuthor.get();
    }
}
