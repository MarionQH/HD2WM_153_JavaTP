package eni.tp.app.eni_app;

import eni.tp.app.eni_app.bll.ArticleManager;
import eni.tp.app.eni_app.bo.Movie;
import eni.tp.app.eni_app.bo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Arrays;
import java.util.List;

//ne pas oublier ce @ pour que le user reste en session sur tout les controller
@SessionAttributes ({"loggedUser"})
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
        //tester si le film existe
        if (movie == null) {
            return "movie-not-found";
        }
        model.addAttribute("movie",movie);
        return "details";
    }

    @GetMapping("login")
    public String getConnectForm(Model model) {

        //tester si déja connecté
        User loggedUser =(User) model.getAttribute("loggedUser");

        if (loggedUser != null) {
            return "already-logged"; // si déjà connecté retourner page erreur
            }

        // Préparer ce que tu va envoyer dans le formulaire par défaut = instancier un user vide
        User user = new User("","");

        // Envoyer le user dans le front/le modèle
        // pour le mettre dans le formulaire
        model.addAttribute("user", user);

        return "connect";

    }

    @PostMapping("login")
    public String postConnectForm(@ModelAttribute User user, Model model,RedirectAttributes redirectAttributes){
        //tu récupère l'user grace au @modelAttribute

        //mettre user en session
        model.addAttribute("loggedUser",user);

        //ajouter un message temporaire
        redirectAttributes.addFlashAttribute("flashMessage","Vous êtes bien connecté");

        //
        return "redirect:accueil";
    }

    @GetMapping("logout")
    public String logout(SessionStatus status) {

        //nettoyer la session (se déconnecter)
        status.setComplete();

        //rediriger à la page d'accueil
        return "redirect:accueil";
    }


}
