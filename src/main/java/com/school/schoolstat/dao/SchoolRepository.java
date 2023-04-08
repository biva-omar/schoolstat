package com.school.schoolstat.dao;

import com.school.schoolstat.models.entities.School;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SchoolRepository extends JpaRepository<School, Long> {
}