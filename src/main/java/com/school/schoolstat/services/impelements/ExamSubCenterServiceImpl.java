package com.school.schoolstat.services.impelements;

import com.school.schoolstat.dao.ExamSubCenterRepository;
import com.school.schoolstat.models.entities.ExamSubCenter;
import com.school.schoolstat.services.interfaces.ExamSubCenterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ExamSubCenterServiceImpl implements ExamSubCenterService {

    @Autowired
    private ExamSubCenterRepository examSubCenterRepository;

    @Override
    public ExamSubCenter createExamSubCenter(ExamSubCenter examSubCenter) {
        return examSubCenterRepository.save(examSubCenter);
    }

    @Override
    public ExamSubCenter retrieveExamSubCenter(Long id) {
        return examSubCenterRepository.findById(id).get();
    }

    @Override
    public List<ExamSubCenter> retrieveExamSubCenter() {
        return examSubCenterRepository.findAll();
    }

    @Override
    public ExamSubCenter updateExamSubCenter(ExamSubCenter examSubCenter) {
        return examSubCenterRepository.save(examSubCenter);
    }

    @Override
    public void deleteExamSubCenter(ExamSubCenter examSubCenter) {
        examSubCenterRepository.delete(examSubCenter);
    }
}
