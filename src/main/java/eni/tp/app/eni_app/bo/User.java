package eni.tp.app.eni_app.bo;

import jakarta.validation.constraints.Email;

public class User {

    //Validation de surface = format des données = annotation Email
    @Email(message = "Vous devez saisir une adresse email valide")
    public String email;
    public String password;

    public User() {
        email = "";
        password = "";
    }

    public User(String email, String password) {
        this.email = email;
        this.password = password;

    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


}
