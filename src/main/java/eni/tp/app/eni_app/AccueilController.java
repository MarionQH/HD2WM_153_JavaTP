package eni.tp.app.eni_app;

import eni.tp.app.eni_app.bll.ArticleManager;
import eni.tp.app.eni_app.bo.Movie;
import eni.tp.app.eni_app.bo.User;
import eni.tp.app.eni_app.ihm.EniFlashMessage;
import eni.tp.app.eni_app.ihm.EniIHMHelpers;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;

//ne pas oublier ce @ pour que le user reste en session sur tout les controller
@SessionAttributes({"loggedUser"})
@Controller
public class AccueilController {

    @Autowired
    ArticleManager articleManager;

    @Autowired
    LocaleResolver localeResolver;

    @GetMapping("accueil")
    public String accueil() {
        return "accueil";
    }

    @GetMapping("movies")
    public String movies(Model model) {
        model.addAttribute("movies", articleManager.getMovies());

        //envoyer la note maximale
        List<Integer> maxStars = Arrays.asList(1, 2, 3, 4, 5);
        model.addAttribute("maxStars", maxStars);

        return "movies";
    }

    @GetMapping("details/{id}")
    public String details(@PathVariable("id") long id, Model model) {
        Movie movie = articleManager.getById(id);
        //tester si le film existe
        if (movie == null) {
            return "movie-not-found";
        }
        model.addAttribute("movie", movie);
        return "details";
    }

    @GetMapping("login")
    public String getConnectForm(Model model, RedirectAttributes redirectAttributes) {

        //tester si déja connecté
        User loggedUser = (User) model.getAttribute("loggedUser");

        if (loggedUser != null) {
            EniIHMHelpers.sendCommonFlashMessage(redirectAttributes, EniFlashMessage.TYPE_FLASH_ERROR, "ERREUR ! Vous êtes déjà connecté(e)");
            return "redirect:accueil"; // si déjà connecté retourner page erreur
        }

        // Préparer ce que tu va envoyer dans le formulaire par défaut = instancier un user vide
        User user = new User("", "");

        // Envoyer le user dans le front/le modèle
        // pour le mettre dans le formulaire
        model.addAttribute("user", user);

        return "connect";

    }

    @PostMapping("login")
    public String postConnectForm(@ModelAttribute User user, Model model, RedirectAttributes redirectAttributes) {
        //tu récupère l'user grace au @modelAttribute

        //mettre user en session
        model.addAttribute("loggedUser", user);

        //ajouter un message temporaire
        // appeler la fonction: EniIHMHelpers
        EniIHMHelpers.sendCommonFlashMessage(redirectAttributes, EniFlashMessage.TYPE_FLASH_SUCESS, "Vous êtes connecté(e) avec succès");

        //
        return "redirect:/accueil";
    }

    @GetMapping("logout")
    public String logout(SessionStatus status, RedirectAttributes redirectAttributes) {

        //nettoyer la session (se déconnecter)
        status.setComplete();

        // appeler la fonction: EniIHMHelpers
        EniIHMHelpers.sendCommonFlashMessage(redirectAttributes, EniFlashMessage.TYPE_FLASH_SUCESS, "Vous êtes déconnecté(e)");

        //rediriger à la page d'accueil
        return "redirect:/accueil";


    }
    /**
     * Soi je vais dans la page formulaire avec un id (donc edition)
     * Ou soi je vais dans la page formulaire sans id (donc creation)
     * @param model
     * @return
     */
    @GetMapping({"addfilm/{id}","addfilm"})
    public String addFilm(@PathVariable(required = false) Long id ,Model model) {

        // Préparer ce que tu va envoyer dans le formulaire par défaut = instancier un film vide
        Movie movie = new Movie("Thelma la licorne", 2024, 93, "Une ponette qui chante en rêvant de célébrité accède instantanément à la notoriété lorsqu'elle se transforme en licorne pailletée.", "thelma.jpg");

        //S il y a un id le film on le récupère grace à l'id
        if (id != null){
            movie = articleManager.getById(id);
        }
        // Envoyer le film dans le front/le modèle
        // pour le mettre dans le formulaire
        model.addAttribute("movie", movie);

        return "form-film";
    }

    @PostMapping("addfilm")
    public String postaddFilm(@Valid @ModelAttribute Movie movie, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        //tu récupère l'user grace au @modelAttribute

        //objectif tester la validité de la donnée (control surface)
        // instaler un librairie (dans build gradle: implementation 'org.springframework.boot:spring-boot-starter-validation')
        //On ajoute @Valid pour savoir quel modèle on veut valider
        if (bindingResult.hasErrors()) {
            System.out.println("erreur de contrôle surface");
            return "form-film";
        }

        articleManager.saveMovie(movie);

        //ajouter un message temporaire
        // appeler la fonction: EniIHMHelpers
        EniIHMHelpers.sendCommonFlashMessage(redirectAttributes, EniFlashMessage.TYPE_FLASH_SUCESS, "Votre film est bien enregistré");

        //
        return "redirect:/addfilm";
    }

    @GetMapping("change-lang/{lang}")
    public String changeLangue(@PathVariable("lang") String lang,HttpServletRequest request,HttpServletResponse response) {
        Locale locale = Locale.forLanguageTag(lang);
        localeResolver.setLocale(request, response, locale);
        return "redirect:/accueil";
    }
}
