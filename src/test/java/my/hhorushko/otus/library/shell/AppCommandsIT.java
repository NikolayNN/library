package my.hhorushko.otus.library.shell;

import my.hhorushko.otus.library.dao.GenreRepository;
import my.hhorushko.otus.library.domain.Genre;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AppCommandsIT {

    @Autowired
    private GenreRepository genreRepository;

    @Test
    public void saveGenre() {

        genreRepository.insert(new Genre("1"));
    }
}