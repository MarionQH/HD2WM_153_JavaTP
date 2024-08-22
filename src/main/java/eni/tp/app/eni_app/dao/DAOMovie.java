package eni.tp.app.eni_app.dao;

import eni.tp.app.eni_app.bo.Movie;
import org.springframework.stereotype.Component;
import java.util.Arrays;
import java.util.List;

@Component
public class DAOMovie implements IDAOMovie {


    List<Movie> movies = Arrays.asList(
            new Movie(1, "Thelma la licorne", 2024, 93, "Une ponette qui chante en rêvant de célébrité accède instantanément à la notoriété lorsqu'elle se transforme en licorne pailletée.", "thelma.jpg"),
            new Movie(2, "L'école des licornes", 2023, 120, "Sur l'île de la Licorne, six jeunes étudiants apprennent à maîtriser les pouvoirs magiques des licornes qui leur ont été confiées.", "unicorn-academy.jpg"),
            new Movie(3, "My Little Pony: Nouvelle gérénation", 2021, 91, "On se déchire à Equestria, mais une petite héroïne avec des étoiles plein les yeux est persuadée que les poneys terrestres, les pégases et les licornes peuvent être amis.", "little-pony.jpg"));

    @Override
    public List<Movie> selectMovies() {
        return movies;
    }

    @Override
    public Movie selectMovieById(long id) {
        //récuperer l'aliment selon l'id
        //utilisation de prédicate = filtrer avce un predicate
        // un filter retourne les éléments filtrés qui respectent la condition
        //.FindFirst permet de retourner un seul élement
        //.get comme c'est nullable je pars du principe qu'il n'est pas null pour l'instant
        //on enlève le point get quand il peut être null
        //.orElse si tu ne trouves pas, la valeur est null
        Movie movieToFound = movies.stream().filter(aliment -> aliment.id == id).findFirst().orElse(null);
        return movieToFound;
    }
}