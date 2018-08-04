package my.hhorushko.otus.library.dao;

import my.hhorushko.otus.library.domain.Author;

import java.util.List;

public interface AuthorRepository extends BaseCrudRepository<Author>{

    List<Author> findById(int[] ids);

    List<Author> findAuthorByBookId(int id);
}
