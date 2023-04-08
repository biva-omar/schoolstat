package com.school.schoolstat.services.impelements;

import com.school.schoolstat.dao.ExamClassroomRepository;
import com.school.schoolstat.models.entities.ExamClassroom;
import com.school.schoolstat.services.interfaces.ExamClassroomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ExamClassroomServiceImpl implements ExamClassroomService {

    @Autowired
    private ExamClassroomRepository examClassroomRepository;

    @Override
    public ExamClassroom createExamClassroom(ExamClassroom examClassroom) {
        return examClassroomRepository.save(examClassroom);
    }

    @Override
    public ExamClassroom retrieveExamClassroom(Long id) {
        return examClassroomRepository.findById(id).get();
    }

    @Override
    public List<ExamClassroom> retrieveExamClassroom() {
        return examClassroomRepository.findAll();
    }

    @Override
    public ExamClassroom updateExamClassroom(ExamClassroom examClassroom) {
        return examClassroomRepository.save(examClassroom);
    }

    @Override
    public void deleteExamClassroom(ExamClassroom examClassroom) {
        examClassroomRepository.delete(examClassroom);
    }
}
