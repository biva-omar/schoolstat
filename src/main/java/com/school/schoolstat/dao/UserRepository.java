package com.school.schoolstat.dao;

import com.school.schoolstat.models.entities.Account;
import com.school.schoolstat.models.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
    /**
     * Retourne les informations de l'utilisateur d'un compte donné
     * @param account Account object
     * @return User object
     */
    public User findByAccount(Account account);
}