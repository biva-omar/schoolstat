package com.school.schoolstat.controllers;

import com.school.schoolstat.models.dto.requests.ExamClassroomRequestDto;
import com.school.schoolstat.models.dto.responses.ExamClassroomResponseDto;
import com.school.schoolstat.models.entities.ExamClassroom;
import com.school.schoolstat.services.interfaces.ExamClassroomService;
import jakarta.websocket.server.PathParam;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("exam-classrooms")
public class ExamClassroomController {

    @Autowired
    private ExamClassroomService examClassroomService;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping("/")
    public ResponseEntity<?> create(@RequestBody ExamClassroomRequestDto examClassroomRequest){
        ExamClassroom examClassroom = modelMapper.map(examClassroomRequest, ExamClassroom.class);

        return ResponseEntity.ok(
            modelMapper.map(examClassroomService.createExamClassroom(examClassroom), ExamClassroomResponseDto.class)
        );
    }

    @GetMapping("/")
    public ResponseEntity<?> retrieve(){
        List<ExamClassroom> examClassrooms = examClassroomService.retrieveExamClassroom();
        return ResponseEntity.ok(
                examClassrooms.stream().map(
                        examClassroom -> modelMapper.map(examClassroom, ExamClassroom.class)
                )
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> retrieve2(@PathVariable("id") ExamClassroom examClassroom){
        return ResponseEntity.ok(
                modelMapper.map(examClassroom, ExamClassroomResponseDto.class)
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable("id") ExamClassroom id, @RequestBody ExamClassroomRequestDto examClassroomRequest){
        ExamClassroom examClassroom = modelMapper.map(examClassroomRequest, ExamClassroom.class);
        examClassroom.setId(id.getId());

        return ResponseEntity.ok(
                modelMapper.map(examClassroomService.updateExamClassroom(examClassroom), ExamClassroom.class)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") ExamClassroom id){
        examClassroomService.deleteExamClassroom(id);
        return ResponseEntity.ok("deleted successful");
    }
}
