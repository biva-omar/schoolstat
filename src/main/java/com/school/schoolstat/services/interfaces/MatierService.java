package com.school.schoolstat.services.interfaces;

import com.school.schoolstat.models.entities.Matiere;

import java.util.List;

public interface MatierService {

    public Matiere createMatiere(Matiere matiere);

    public Matiere retrieveMatiere( Long id);

    public List<Matiere> retrieveMatiere();

    public Matiere updateMatiere(Matiere matiere);

    public void deleteMatiere(Matiere matiere);

    Long countMatiere();
}
