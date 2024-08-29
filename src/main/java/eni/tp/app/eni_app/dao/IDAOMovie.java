package eni.tp.app.eni_app.dao;

import eni.tp.app.eni_app.bo.Movie;

import java.util.List;

public interface IDAOMovie {

    public List<Movie> selectMovies();

    public Movie selectMovieById(Long id);

    public void save(Movie movie);
}
