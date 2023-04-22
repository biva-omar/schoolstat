package com.school.schoolstat.controllers;

import com.school.schoolstat.models.dto.requests.MatiereRequestDto;
import com.school.schoolstat.models.dto.responses.MatiereResponseDto;
import com.school.schoolstat.models.entities.Matiere;
import com.school.schoolstat.services.interfaces.MatierService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("matieres")
public class MatiereController {

    @Autowired
    private MatierService matiereService;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping("/")
    public ResponseEntity<?> create(@RequestBody MatiereRequestDto matiereRequest){
        Matiere matiere = modelMapper.map(matiereRequest, Matiere.class);

        return ResponseEntity.ok(
                matiereService.createMatiere(matiere)
        );
    }

    @GetMapping("/")
    public ResponseEntity<?> retrieve(){
        List<Matiere> matieres = matiereService.retrieveMatiere();

        return ResponseEntity.ok(
                matieres.stream().map(
                        matiere -> modelMapper.map(matiere, MatiereResponseDto.class)
                )
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> retrieve2(@PathVariable("id") Matiere matiere){
        return ResponseEntity.ok(
                modelMapper.map(matiere, MatiereResponseDto.class)
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable("id") Matiere id, @RequestBody MatiereRequestDto matiereRequest){
        Matiere matiere = modelMapper.map(matiereRequest, Matiere.class);
        matiere.setId(id.getId());

        return ResponseEntity.ok(
                modelMapper.map(matiereService.updateMatiere(matiere), Matiere.class)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Matiere id){
        matiereService.deleteMatiere(id);
        return ResponseEntity.ok(
                "deleted successful"
        );
    }

    @GetMapping("/count")
    public ResponseEntity<?> count(){
        Long total = matiereService.countMatiere();
        return ResponseEntity.ok(total);
    }
}
