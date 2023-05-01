package com.school.schoolstat.controllers;

import com.school.schoolstat.models.dto.requests.SchoolRequestDto;
import com.school.schoolstat.models.dto.responses.GraphResponseDto;
import com.school.schoolstat.models.dto.responses.SchoolResponseDto;
import com.school.schoolstat.models.entities.Note;
import com.school.schoolstat.models.entities.School;
import com.school.schoolstat.services.interfaces.SchoolService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("schools")
public class SchoolController {

    @Autowired
    private SchoolService schoolService;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping("/")
    public ResponseEntity<?> create(@RequestBody SchoolRequestDto schoolRequest){
        School school = modelMapper.map(schoolRequest, School.class);

        school.setId(null);
        return ResponseEntity.ok(
                modelMapper.map(schoolService.createSchool(school), SchoolResponseDto.class)
        );
    }

    @GetMapping("/")
    public ResponseEntity<?> retrieve(){
        List<School> schools = schoolService.retrieveSchool();

        return ResponseEntity.ok(
                schools.stream().map(
                        school -> modelMapper.map(school, SchoolResponseDto.class)
                )
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> retrieve2(@PathVariable("id") School school){
        return ResponseEntity.ok(
                modelMapper.map(school, SchoolResponseDto.class)
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable("id") School id, @RequestBody SchoolRequestDto schoolRequest){
        School school = modelMapper.map(schoolRequest, School.class);
        school.setId(id.getId());

        return ResponseEntity.ok(
                modelMapper.map(schoolService.updateSchool(school), SchoolResponseDto.class)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") School id){
        schoolService.deleteSchool(id);
        return ResponseEntity.ok("deleted");
    }

    @GetMapping("/count")
    public ResponseEntity<?> count(){
        Long total = schoolService.countSchool();
        return ResponseEntity.ok(total);
    }

    @GetMapping("/count-by-sub-centers")
    public ResponseEntity<?> countStudentsBySchools(){
        List<GraphResponseDto> data = schoolService.countSchoolsBySubCenters();

        return ResponseEntity.ok(data);
    }
}
