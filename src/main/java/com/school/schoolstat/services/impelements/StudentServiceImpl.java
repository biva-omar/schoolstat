package com.school.schoolstat.services.impelements;

import com.school.schoolstat.dao.StudentRepository;
import com.school.schoolstat.models.entities.Student;
import com.school.schoolstat.services.interfaces.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@Transactional
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Override
    public Student createStudent(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public Student retrieveStudent(Long id) {
        return studentRepository.findById(id).get();
    }

    @Override
    public List<Student> retrieveStudent() {
        return studentRepository.findAll();
    }

    @Override
    public Student updateStudent(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public void deleteStudent(Student student) {
        studentRepository.delete(student);
    }
}
