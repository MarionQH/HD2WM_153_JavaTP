package eni.tp.app.eni_app;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AccueilController {

    @GetMapping("accueil")
    public String accueil() {
        return "accueil";
    }

    @GetMapping("movies")
    public String movies() {
        return "movies";
    }

    @GetMapping("details")
    public String details() {
        return "details";
    }
}
