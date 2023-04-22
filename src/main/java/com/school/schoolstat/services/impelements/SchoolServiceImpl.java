package com.school.schoolstat.services.impelements;

import com.school.schoolstat.dao.SchoolRepository;
import com.school.schoolstat.models.entities.School;
import com.school.schoolstat.services.interfaces.SchoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class SchoolServiceImpl implements SchoolService {

    @Autowired
    private SchoolRepository schoolRepository;

    @Override
    public School createSchool(School school)
    {
        school.setId(null);
        return schoolRepository.save(school);
    }

    @Override
    public School retrieveSchool(Long id) {
        return schoolRepository.findById(id).get();
    }

    @Override
    public List<School> retrieveSchool() {
        return schoolRepository.findAll();
    }

    @Override
    public School updateSchool(School school) {
        return schoolRepository.save(school);
    }

    @Override
    public void deleteSchool(School school) {
        schoolRepository.delete(school);
    }

    @Override
    public Long countSchool() {
        return schoolRepository.count();
    }
}
