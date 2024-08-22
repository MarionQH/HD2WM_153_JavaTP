package eni.tp.app.eni_app;

import eni.tp.app.eni_app.bll.ArticleManager;
import eni.tp.app.eni_app.bo.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Arrays;
import java.util.List;

@Controller
public class AccueilController {

    @Autowired
    ArticleManager articleManager;

    @GetMapping("accueil")
    public String accueil() {
        return "accueil";
    }

    @GetMapping("movies")
    public String movies(Model model) {
        model.addAttribute("movies",articleManager.getMovies());

        //envoyer la note maximale
        List<Integer> maxStars = Arrays.asList(1,2,3,4,5);
        model.addAttribute("maxStars",maxStars);

        return "movies";
    }

    @GetMapping("details/{id}")
    public String details(@PathVariable ("id") long id, Model model) {
        Movie movie = articleManager.getById(id);
        model.addAttribute("movie",movie);
        return "details";
    }


}
