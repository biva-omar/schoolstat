package com.school.schoolstat.services.interfaces;

import com.school.schoolstat.models.entities.ExamCenter;

import java.util.List;

public interface ExamCenterService {

    public ExamCenter createExamCenter(ExamCenter examCenter);

    public ExamCenter retrieveExamCenter(Long id);

    public List<ExamCenter> retrieveExamCenter();

    public ExamCenter updateExamCenter(ExamCenter examCenter);

    public void deleteExamCenter(ExamCenter examCenter);
}
