package com.school.schoolstat.dao;

import com.school.schoolstat.models.entities.ExamClassroom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExamClassroomRepository extends JpaRepository<ExamClassroom, Long> {
}