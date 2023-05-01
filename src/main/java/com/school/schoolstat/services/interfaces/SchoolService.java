package com.school.schoolstat.services.interfaces;

import com.school.schoolstat.models.dto.responses.GraphResponseDto;
import com.school.schoolstat.models.entities.ExamCenter;
import com.school.schoolstat.models.entities.School;

import java.util.List;

public interface SchoolService {
    public School createSchool(School school);

    public School retrieveSchool(Long id);

    public List<School> retrieveSchool();

    public School updateSchool(School school);

    public void deleteSchool(School school);

    Long countSchool();


    List<GraphResponseDto> countSchoolsBySubCenters();
}
