package com.school.schoolstat.dao;

import com.school.schoolstat.models.entities.Matiere;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MatiereRepository extends JpaRepository<Matiere, Long> {
}