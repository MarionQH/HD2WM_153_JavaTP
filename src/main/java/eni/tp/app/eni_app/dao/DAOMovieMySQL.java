package eni.tp.app.eni_app.dao;

import eni.tp.app.eni_app.bo.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Profile("MySQL")
@Component
public class DAOMovieMySQL implements IDAOMovie {

    @Autowired
    JdbcTemplate jdbcTemplate;

    /**
     * LE code qui permet de savoir comment convertir/mapper un resultat en SQL en Objet/Classe
     * Comment mapper un resultat SQL en Movie
     */
    static final RowMapper<Movie> MOVIE_ROW_MAPPER = new RowMapper<Movie>() {

        @Override
        public Movie mapRow(ResultSet rs, int rowNum) throws SQLException {
            Movie movie = new Movie();
            movie.id = rs.getInt("id");
            movie.title = rs.getString("title");
            movie.year = rs.getInt("year");
            movie.duration = rs.getInt("duration");
            movie.synopsis = rs.getString("synopsis");
            movie.note = rs.getInt("note");
            movie.url = rs.getString("url");
            return movie;
        }
    };

    @Override
    public List<Movie> selectMovies() {
        return jdbcTemplate.query("SELECT * FROM childrenmovie", MOVIE_ROW_MAPPER);

    }

    @Override
    public Movie selectMovieById(long id) {
        List<Movie> movies = jdbcTemplate.query("SELECT * FROM childrenmovie WHERE id = ?", MOVIE_ROW_MAPPER,id);
        if (movies.size() == 0) {
            return null;
        }
        //0 correspond à l'index de la liste (premier élément)
        return movies.get(0);
    }

    @Override
    public void save(Movie movie) {
        //Insérer en base un aliment

        jdbcTemplate.update("INSERT INTO childrenmovie (title,year,duration,synopsis,url) VALUES (?,?,?,?,?)", movie.title, movie.year, movie.duration, movie.synopsis, movie.url);
    }

}
