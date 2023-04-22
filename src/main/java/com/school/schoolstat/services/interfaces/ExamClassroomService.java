package com.school.schoolstat.services.interfaces;

import com.school.schoolstat.models.entities.ExamClassroom;

import java.util.List;

public interface ExamClassroomService {

    public ExamClassroom createExamClassroom(ExamClassroom examClassroom);

    public ExamClassroom retrieveExamClassroom(Long id);

    public List<ExamClassroom> retrieveExamClassroom();

    public ExamClassroom updateExamClassroom(ExamClassroom examClassroom);

    public void  deleteExamClassroom(ExamClassroom examClassroom);

    Long countExamClassroom();
}
