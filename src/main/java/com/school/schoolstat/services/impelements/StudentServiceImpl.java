package com.school.schoolstat.services.impelements;

import com.school.schoolstat.dao.StudentRepository;
import com.school.schoolstat.models.dto.responses.Etat1ResponseDto;
import com.school.schoolstat.models.dto.responses.Etat2ResponseDto;
import com.school.schoolstat.models.dto.responses.Etat3ResponseDto;
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
        student.setId(null);
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

    @Override
    public Long count() {
        return studentRepository.count();
    }

    @Override
    public List<Etat1ResponseDto> count(Long subCenter, String sex) {
        if (subCenter == null){
            List<Etat1ResponseDto> students = studentRepository.findBySubCenters();
            return students;
        }
        return null;
    }

    @Override
    public List<Etat1ResponseDto> countState1() {
            return studentRepository.findBySubCenters();
    }

    @Override
    public List<Etat2ResponseDto> countState2() {
        return studentRepository.findBySubCenters2();
    }

    @Override
    public List<Etat3ResponseDto> countState3() {
        return studentRepository.findByTeachingOrders();
    }
}
