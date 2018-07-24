package my.hhorushko.otus.library.dao;

import lombok.AllArgsConstructor;
import my.hhorushko.otus.library.domain.Author;
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
public class AuthorDaoImpl implements AuthorDao {

    private final NamedParameterJdbcTemplate jdbc;

    @Override
    public Author findByName(String name){

        SqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("name", name);
        return jdbc.queryForObject("select * from author WHERE name=:name", parameters, new AuthorMapper());
    }

    @Override
    public Author findById(int id){

        SqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("id", id);
        return jdbc.queryForObject("select * from author WHERE id=:id", parameters, new AuthorMapper());
    }

    @Override
    public void deleteById(int id){

        SqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("id", id);

        jdbc.update("delete FROM author WHERE id=:id;", parameters);
    }

    @Override
    public Author update(Author author){

        SqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("id", author.getId())
                .addValue("name", author.getName());

        jdbc.update("UPDATE author SET name=:name WHERE id=:id;", parameters);

        return author;
    }

    @Override
    public List<Author> findAll(){

        return jdbc.query("select * from author", new AuthorMapper());
    }

    @Override
    public Author insert(Author author){

        int id = generateId();

        SqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("id", id)
                .addValue("name", author.getName());

        jdbc.update("insert into author (id, name) values (:id, :name)", parameters);

        author.setId(id);
        return author;
    }

    @Override
    public List<Author> findAuthorByBookId(int id){
        SqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("id", id);
        return jdbc.query("SELECT Author.id, Author.name " +
                        "FROM book_author " +
                        "LEFT JOIN author " +
                        "ON Book_Author.author_id = Author.id " +
                        "WHERE book_id = :id",
                parameters, new AuthorMapper());

    }

    private int generateId(){
        SqlParameterSource parameters = new MapSqlParameterSource();

        int id = jdbc.queryForObject("select max(id) from author", parameters, Integer.class) + 1;

        return id;
    }

    class AuthorMapper implements RowMapper<Author> {

        @Override
        public Author mapRow(ResultSet rs, int rowNum) throws SQLException {
            Author author = new Author();
            author.setId(rs.getInt("id"));
            author.setName(rs.getString("name"));
            return author;
        }
    }
}
