package com.school.schoolstat.controllers;

import com.school.schoolstat.models.dto.requests.StudentRequestDto;
import com.school.schoolstat.models.dto.responses.StudentResponseDto;
import com.school.schoolstat.models.entities.Student;
import com.school.schoolstat.services.interfaces.StudentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping("/")
    public ResponseEntity<?> create(@RequestBody StudentRequestDto studentRequest){
        Student student = modelMapper.map(studentRequest, Student.class);

        return ResponseEntity.ok(
                modelMapper.map(studentService.createStudent(student), StudentResponseDto.class)
        );
    }

    @GetMapping("/")
    public ResponseEntity<?> retrieve(){
        List<Student> students = studentService.retrieveStudent();

        return ResponseEntity.ok(
                students.stream().map(
                        student -> modelMapper.map(student, StudentResponseDto.class)
                )
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> retrieve2(@PathVariable("id") Student student){
        return ResponseEntity.ok(
                modelMapper.map(student, StudentResponseDto.class)
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable("id") Student id, @RequestBody StudentRequestDto studentRequest){
        Student student = modelMapper.map(studentRequest, Student.class);
        student.setId(id.getId());

        return ResponseEntity.ok(
                modelMapper.map(studentService.updateStudent(student), StudentResponseDto.class)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Student id){
        studentService.deleteStudent(id);
        return ResponseEntity.ok("deleted successful");
    }
}
