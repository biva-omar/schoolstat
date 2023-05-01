package com.school.schoolstat.dao;

import com.school.schoolstat.models.entities.Session;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SessionRepository extends JpaRepository<Session, String> {
    /**
     * Retourne les sessions d'un compte donné
     * @param username
     * @param page objet de pagination
     * @return page d'objets Session
     */
    public Page<Session> findByUsername(String username, Pageable page);
}