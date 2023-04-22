package com.school.schoolstat.controllers;

import com.school.schoolstat.models.dto.requests.ExamSubCenterRequestDto;
import com.school.schoolstat.models.dto.responses.ExamSubCenterResponseDto;
import com.school.schoolstat.models.entities.ExamSubCenter;
import com.school.schoolstat.services.interfaces.ExamSubCenterService;
import jakarta.websocket.server.PathParam;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("exam-sub-centers")
public class ExamSubCenterController {

    @Autowired
    private ExamSubCenterService examSubCenterService;

    @Autowired private ModelMapper modelMapper;

    @PostMapping("/")
    public ResponseEntity<?> create(@RequestBody ExamSubCenterRequestDto examSubCenterRequest){
        ExamSubCenter examSubCenter = modelMapper.map(examSubCenterRequest, ExamSubCenter.class);

        return ResponseEntity.ok(
                modelMapper.map(examSubCenterService.createExamSubCenter(examSubCenter), ExamSubCenterResponseDto.class)
        );
    }

    @GetMapping("/")
    public ResponseEntity<?> retrieve(){
        List<ExamSubCenter> examSubCenters = examSubCenterService.retrieveExamSubCenter();

        return ResponseEntity.ok(
                examSubCenters.stream().map(
                        examSubCenter -> modelMapper.map(examSubCenter, ExamSubCenterResponseDto.class)
                )
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> retrieve2(@PathVariable("id") ExamSubCenter examSubCenter){
        return ResponseEntity.ok(
                modelMapper.map(examSubCenter, ExamSubCenterResponseDto.class)
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable("id") ExamSubCenter id, @RequestBody ExamSubCenter examSubCenter){
        examSubCenter.setId(id.getId());
        return ResponseEntity.ok(
                examSubCenterService.updateExamSubCenter(examSubCenter)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") ExamSubCenter examSubCenter){
        examSubCenterService.deleteExamSubCenter(examSubCenter);
        return ResponseEntity.ok("deleted successful");
    }

    @GetMapping("/count")
    public ResponseEntity<?> count(){
        Long total = examSubCenterService.count();
        return ResponseEntity.ok(total);
    }
    
}
