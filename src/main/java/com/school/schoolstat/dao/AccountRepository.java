package com.school.schoolstat.dao;

import com.school.schoolstat.models.entities.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AccountRepository extends JpaRepository<Account, String> {
    /**
     * Retourne un compte ayant un email ou un numéro de tépélphone donné
     *
     * @param emailOrPhone
     * @return Account object
     */
    @Query("select a from Account a  WHERE a.email LIKE :emailOrPhone OR a.phone LIKE :emailOrPhone")
    public Account findByEmailOrPhone(String emailOrPhone);
}