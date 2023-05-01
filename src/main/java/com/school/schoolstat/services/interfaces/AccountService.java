package com.school.schoolstat.services.interfaces;

import com.school.schoolstat.models.entities.Account;

public interface AccountService {
    /**
     * Service permettant de trouver un compte ayant un email ou numéro de
     * téléphone donné
     *
     * @param username
     * @return un objet Account
     */
    public Account loadUserByUsername(String username);

    /**
     * Service permettant d'avoir le nombre de comptes dans la base de données
     *
     * @return un Long
     */
    public Long countAllAccounts();
}
