package my.hhorushko.otus.library.dao;

import lombok.AllArgsConstructor;
import my.hhorushko.otus.library.domain.Author;
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
public class GenreDaoImpl implements GenreDao{


    private final NamedParameterJdbcTemplate jdbc;

    @Override
    public Genre findByName(String name){

        SqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("name", name);
        return jdbc.queryForObject("select * from genre WHERE name=:name", parameters, new GenreMapper());
    }

    @Override
    public Genre findById(int id){

        SqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("id", id);
        return jdbc.queryForObject("select * from genre WHERE id=:id", parameters, new GenreMapper());
    }

    @Override
    public void deleteById(int id){

        SqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("id", id);

        jdbc.update("delete FROM genre WHERE id=:id;", parameters);
    }

    @Override
    public Genre update(Genre genre){

        SqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("id", genre.getId())
                .addValue("name", genre.getName());

        jdbc.update("UPDATE genre SET name=:name WHERE id=:id;", parameters);

        return genre;
    }

    @Override
    public List<Genre> findAll(){

        return jdbc.query("select * from genre", new GenreMapper());
    }

    @Override
    public Genre insert(Genre genre){

        int id = generateId();

        SqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("id", id)
                .addValue("name", genre.getName());

        jdbc.update("insert into genre (id, name) values (:id, :name)", parameters);

        genre.setId(id);
        return genre;
    }

    private int generateId(){
        SqlParameterSource parameters = new MapSqlParameterSource();

        int id = jdbc.queryForObject("select max(id) from genre", parameters, Integer.class) + 1;

        return id;
    }

    class GenreMapper implements RowMapper<Genre> {

        @Override
        public Genre mapRow(ResultSet rs, int rowNum) throws SQLException {
            Genre genre = new Genre();
            genre.setId(rs.getInt("id"));
            genre.setName(rs.getString("name"));
            return genre;
        }
    }

}
