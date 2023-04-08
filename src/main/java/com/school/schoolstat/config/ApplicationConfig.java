package com.school.schoolstat.config;

import com.school.schoolstat.models.dto.requests.NoteRequestDto;
import com.school.schoolstat.models.dto.requests.StudentRequestDto;
import com.school.schoolstat.models.entities.Note;
import com.school.schoolstat.models.entities.Student;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfig {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();


        /*PropertyMap<NoteRequestDto, Note> propertyMap = new PropertyMap<NoteRequestDto, Note> (){
            protected void configure() {
                map(source.getMatiereId()).getMatiere();
            }
        };*/
        modelMapper.getConfiguration().setAmbiguityIgnored(true);
        return modelMapper;
    }

}
