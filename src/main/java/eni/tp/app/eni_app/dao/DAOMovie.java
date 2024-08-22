package eni.tp.app.eni_app.dao;

import eni.tp.app.eni_app.bo.Movie;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DAOMovie implements IDAOMovie {

    @Override
    public List<Movie> selectMovies () {
        List<Movie> movies = new ArrayList<Movie>();
        movies.add(new Movie(1,"Thelma la licorne", 2024,93,"Une ponette qui chante en rêvant de célébrité accède instantanément à la notoriété lorsqu'elle se transforme en licorne pailletée." ));
        movies.add(new Movie(2,"L'école des licornes", 2023,120,"Sur l'île de la Licorne, six jeunes étudiants apprennent à maîtriser les pouvoirs magiques des licornes qui leur ont été confiées."));
        return movies;
    }
}
