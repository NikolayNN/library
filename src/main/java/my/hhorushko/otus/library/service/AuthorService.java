package my.hhorushko.otus.library.service;

import my.hhorushko.otus.library.domain.Author;

import java.util.List;

public interface AuthorService extends BaseCrudService<Author> {

    List<Author> findById(int[] ids);
}
