package eni.tp.app.eni_app;

import eni.tp.app.eni_app.bll.ArticleManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

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
        return "movies";
    }

    @GetMapping("details")
    public String details() {
        return "details";
    }


}
