package eni.tp.app.eni_app.dao;

import eni.tp.app.eni_app.bo.User;

public interface IDAOAuth {

    /**
     * Permettera de récupérer un utilisateur dans les données
     * @param email
     * @param password
     * @return
     */
    User login(String email, String password);
}
