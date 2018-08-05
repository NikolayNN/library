package my.hhorushko.otus.library.dao;

import my.hhorushko.otus.library.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {


    Optional<User> findByName(String name);
}
