package com.school.schoolstat.dao;

import com.school.schoolstat.models.entities.ExamCenter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExamCenterRepository extends JpaRepository<ExamCenter, Long> {
}