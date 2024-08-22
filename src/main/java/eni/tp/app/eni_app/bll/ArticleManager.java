package eni.tp.app.eni_app.bll;

import eni.tp.app.eni_app.bo.Movie;
import eni.tp.app.eni_app.dao.IDAOMovie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ArticleManager {

    @Autowired
    IDAOMovie daoMovie;

   public List<Movie> getMovies() {

       List<Movie> movies = daoMovie.selectMovies();
       return movies;
   }

}
