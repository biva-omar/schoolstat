package com.school.schoolstat.controllers;

import com.school.schoolstat.dao.ExamCenterRepository;
import com.school.schoolstat.models.dto.requests.ExamCenterRequestDto;
import com.school.schoolstat.models.dto.responses.ExamCenterResponseDto;
import com.school.schoolstat.models.entities.ExamCenter;
import com.school.schoolstat.services.interfaces.ExamCenterService;
import jakarta.websocket.server.PathParam;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("exam-centers")
public class ExamCenterController {

    @Autowired
    private ExamCenterService examCenterService;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping("/")
    public ResponseEntity<?> create(@RequestBody ExamCenterRequestDto examCenterRequest){
        ExamCenter examCenter = modelMapper.map(examCenterRequest, ExamCenter.class);

        return ResponseEntity.ok(
                modelMapper.map(examCenterService.createExamCenter(examCenter), ExamCenterResponseDto.class)
        );
    }

    @GetMapping("/")
    public ResponseEntity<?> retrieve() throws Exception {
        List<ExamCenter> examCenters = examCenterService.retrieveExamCenter();

        return ResponseEntity.ok(
                examCenters.stream().map(
                        ec -> modelMapper.map(ec, ExamCenterResponseDto.class)
                )
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> retrieve2(@PathVariable("id") ExamCenter examCenter) throws Exception{
        return ResponseEntity.ok(
                modelMapper.map(examCenter, ExamCenterResponseDto.class)
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable("id") ExamCenter id, @RequestBody ExamCenterRequestDto examCenterRequest){
        ExamCenter examCenter = modelMapper.map(examCenterRequest, ExamCenter.class);
        examCenter.setId(id.getId());

        return ResponseEntity.ok(
                modelMapper.map(examCenterService.updateExamCenter(examCenter), ExamCenterResponseDto.class)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") ExamCenter id){
        examCenterService.deleteExamCenter(id);
        return ResponseEntity.ok(
                "deleted successful"
        );
    }
}
