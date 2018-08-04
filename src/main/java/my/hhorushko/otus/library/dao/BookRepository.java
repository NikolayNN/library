package my.hhorushko.otus.library.dao;

import my.hhorushko.otus.library.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Integer> {

    Optional<Book> findByName(String name);
}
