package com.school.schoolstat.dao;

import com.school.schoolstat.models.dto.responses.GraphResponseDto;
import com.school.schoolstat.models.entities.School;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SchoolRepository extends JpaRepository<School, Long> {

    /***
     * get schools count by  sub center
     */
    @Query("select new com.school.schoolstat.models.dto.responses.GraphResponseDto(" +
            "sc.label, " +
            "count(s.label))" +
            " from School s join ExamSubCenter sc on s.examSubCenter=sc.id  " +
            " group by sc.label")
    List<GraphResponseDto> countBySubCenters();
}