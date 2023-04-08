package com.school.schoolstat.services.impelements;

import com.school.schoolstat.dao.ExamCenterRepository;
import com.school.schoolstat.models.entities.ExamCenter;
import com.school.schoolstat.services.interfaces.ExamCenterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ExamCenterServiceImpl implements ExamCenterService {

    @Autowired
    private ExamCenterRepository examCenterRepository;

    @Override
    public ExamCenter createExamCenter(ExamCenter examCenter) {
        return examCenterRepository.save(examCenter);
    }

    @Override
    public ExamCenter retrieveExamCenter(Long id) {
        return examCenterRepository.findById(id).get();
    }

    @Override
    public List<ExamCenter> retrieveExamCenter() {
        return examCenterRepository.findAll();
    }

    @Override
    public ExamCenter updateExamCenter(ExamCenter examCenter) {
        return examCenterRepository.save(examCenter);
    }

    @Override
    public void deleteExamCenter(ExamCenter examCenter) {
        examCenterRepository.delete(examCenter);
    }
}
