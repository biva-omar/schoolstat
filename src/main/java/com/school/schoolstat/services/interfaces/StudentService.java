package com.school.schoolstat.services.interfaces;

import com.school.schoolstat.models.entities.Student;

import java.util.List;

public interface StudentService {

    public Student createStudent(Student student);

    public Student retrieveStudent(Long id);

    public List<Student> retrieveStudent();

    public  Student updateStudent(Student student);

    public void deleteStudent(Student student);
}
