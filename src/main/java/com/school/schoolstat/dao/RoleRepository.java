package com.school.schoolstat.dao;

import com.school.schoolstat.models.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, String> {
    /**
     * Recherche le rôle ayant un nom donné
     * @param name
     * @return un objet rôle
     */
    public Role findByName(String name);
}