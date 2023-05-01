package com.school.schoolstat.dao;

import com.school.schoolstat.models.dto.responses.Etat1ResponseDto;
import com.school.schoolstat.models.dto.responses.Etat2ResponseDto;
import com.school.schoolstat.models.dto.responses.Etat3ResponseDto;
import com.school.schoolstat.models.dto.responses.GraphResponseDto;
import com.school.schoolstat.models.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {

    /**
     * Get etat1, count student by every sub-center
     */
    @Query("select new com.school.schoolstat.models.dto.responses.Etat1ResponseDto(" +
            "esc.label, " +
            "count(case s.sex when 'M' then 1 else null end), " +
            "count(case s.sex when 'F' then 1 else null end), " +
            "count(case s.isPresent when false then (case s.sex when 'M' then 1 else null end) else null end), " +
            "count(case s.isPresent when false then (case s.sex when 'F' then 1 else null end) else null end)) " +
            "from Student s join School sc on s.school=sc.id " +
            "join ExamSubCenter esc on sc.examSubCenter=esc.id " +
            "group by esc.label")
    List<Etat1ResponseDto> findBySubCenters();

    /**
     * Get etat2, count student by every sub-
     */
    @Query("select new com.school.schoolstat.models.dto.responses.Etat2ResponseDto(" +
            "esc.label, " +
            "count(s.id), " +
            "count(case s.isPresent when false then 1 else null end) ) " +
            "from Student s join School sc on s.school=sc.id " +
            "join ExamSubCenter esc on sc.examSubCenter=esc.id " +
            "group by esc.label")
    List<Etat2ResponseDto> findBySubCenters2();

    @Query("select new com.school.schoolstat.models.dto.responses.Etat1ResponseDto(" +
            "esc.label, " +
            "count(case s.sex when 'M' then 1 else null end), " +
            "count(case s.sex when 'F' then 1 else null end), " +
            "count(case s.isPresent when false then 1 else null end), " +
            "count(null))" +
            "from Student s join School sc on s.school=sc.id " +
            "join ExamSubCenter esc on sc.examSubCenter=esc.id " +
            "where esc.id=1 group by esc.label ")
    List<Etat1ResponseDto> findBySubCenter(Long subCenter);

    @Query("select esc.label as sousCentre, count(case s.sex when 'M' then 1 else null end) as inscritM, count(case s.sex when 'F' then 1 else null end) as inscritF from Student s join School sc on s.school=sc.id join ExamSubCenter esc on sc.examSubCenter=esc.id where esc.id=2 group by esc.label")
    List<String> findBySubCenter2(Long subCenter);

    @Query("select esc.label, count(s.id) as total from Student s join School sc on s.school=sc.id join ExamSubCenter esc on sc.examSubCenter=esc.id group by esc.label")
    List<String> findBySubCenterAndSex(Long subCenter);

    /***
     * get students count by teaching order
     */
    @Query("select new com.school.schoolstat.models.dto.responses.Etat3ResponseDto(" +
            "sc.teachingOrder, " +
            "count(case s.sex when 'M' then 1 else null end), " +
            "count(case s.sex when 'F' then 1 else null end), " +
            "count(case s.isPresent when false then (case s.sex when 'M' then 1 else null end) else null end), " +
            "count(case s.isPresent when false then (case s.sex when 'F' then 1 else null end) else null end), " +
            "count(null), " +
            "count(null))" +
            " from Student s join School sc on s.school=sc.id  " +
            "join ExamSubCenter esc on sc.examSubCenter=esc.id " +
            " group by sc.teachingOrder")
    List<Etat3ResponseDto> findByTeachingOrders();


    /***
     * get students count by teaching order
     */
    @Query("select new com.school.schoolstat.models.dto.responses.GraphResponseDto(" +
            "esc.label, " +
            "count(s.firstname))" +
            " from Student s join School sc on s.school=sc.id  " +
            "join ExamSubCenter esc on sc.examSubCenter=esc.id " +
            " group by esc.label")
    List<GraphResponseDto> countBySubCenters();

    /***
     * get students count by schools
     */
    @Query("select new com.school.schoolstat.models.dto.responses.GraphResponseDto(" +
            "sc.label, " +
            "count(s.firstname))" +
            " from Student s join School sc on s.school=sc.id  " +
            " group by sc.label")
    List<GraphResponseDto> countBySchools();

}