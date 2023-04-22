package com.school.schoolstat.services.interfaces;

import com.school.schoolstat.models.entities.ExamSubCenter;

import java.util.List;

public interface ExamSubCenterService {

    public ExamSubCenter createExamSubCenter(ExamSubCenter examSubCenter);

    public ExamSubCenter retrieveExamSubCenter(Long id);

    public List<ExamSubCenter> retrieveExamSubCenter();

    public ExamSubCenter updateExamSubCenter(ExamSubCenter examSubCenter);

    public void deleteExamSubCenter(ExamSubCenter examSubCenter);

    Long count();
}
