package my.hhorushko.otus.library.dao;

import lombok.AllArgsConstructor;
import my.hhorushko.otus.library.domain.Book;
import my.hhorushko.otus.library.domain.Genre;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
@AllArgsConstructor
public class BookDaoImpl implements BookDao {

    private final NamedParameterJdbcTemplate jdbc;
    private final AuthorDao authorDao;
    private final GenreDao genreDao;

    private final String findSql = "Select Book.id, Book.name, Genre.id, Genre.name " +
            "FROM Book " +
            "Left Join Genre " +
            "ON Book.genre_id = Genre.id ";

    @Override
    public Book findBookById(int id){
        SqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("id", id);
        return jdbc.queryForObject( findSql + "WHERE Book.id=:id", parameters, new BookMapper());
    }

    @Override
    public List<Book> findAll(){
        return jdbc.query(findSql, new BookMapper());
    }

    @Override
    public Book findByName(String name){
        SqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("name", name);
        return jdbc.queryForObject(findSql + "WHERE Book.name=:id", parameters, new BookMapper());
    }

    @Override
    public Book insert(String bookName, int genreId, int[] authorIds){

        int id = generateId();

        SqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("id", id)
                .addValue("name", bookName)
                .addValue("genreId", genreId);

        jdbc.update("insert into book (id, name, genre_id) values (:id, :name, :genreId)", parameters);

        for (int authorId : authorIds) {

            parameters = new MapSqlParameterSource()
                    .addValue("bookId", id)
                    .addValue("authorId", authorId);

            jdbc.update("insert into book_author (book_id, author_id) values (:bookId, :authorId)", parameters);
        }

        Book book = new Book();
        book.setId(id);
        book.setGenre(genreDao.findById(genreId));
        book.setAuthors(authorDao.findAuthorByBookId(id));
        return book;
    }

    @Override
    public void delete(int id) {
        SqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("id", id);

        jdbc.update("DELETE FROM Book_Author WHERE book_id=:id", parameters);
        jdbc.update("DELETE FROM Book WHERE id=:id", parameters);
    }

    private int generateId(){

        SqlParameterSource parameters = new MapSqlParameterSource();

        int id = jdbc.queryForObject("select max(id) from Book", parameters, Integer.class) + 1;
        return id;
    }

    class BookMapper implements RowMapper<Book> {

        @Override
        public Book mapRow(ResultSet rs, int rowNum) throws SQLException {

            Book book = new Book();
            book.setId(rs.getInt(1));
            book.setName(rs.getString(2));

            Genre genre = new Genre();
            genre.setId(rs.getInt(3));
            genre.setName(rs.getString(4));
            book.setGenre(genre);

            book.setAuthors(authorDao.findAuthorByBookId(book.getId()));
            return book;
        }
    }
}
