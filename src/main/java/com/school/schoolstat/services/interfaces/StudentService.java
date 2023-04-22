package com.school.schoolstat.services.interfaces;

import com.school.schoolstat.models.dto.responses.Etat1ResponseDto;
import com.school.schoolstat.models.dto.responses.Etat2ResponseDto;
import com.school.schoolstat.models.dto.responses.Etat3ResponseDto;
import com.school.schoolstat.models.entities.Student;

import java.util.List;

public interface StudentService {

    public Student createStudent(Student student);

    public Student retrieveStudent(Long id);

    public List<Student> retrieveStudent();

    public  Student updateStudent(Student student);

    public void deleteStudent(Student student);

    Long count();

    List<Etat1ResponseDto> count(Long subCenter, String sex);

    List<Etat1ResponseDto> countState1();

    List<Etat2ResponseDto> countState2();

    List<Etat3ResponseDto> countState3();
}
