package com.school.schoolstat.services.impelements;

import com.school.schoolstat.dao.MatiereRepository;
import com.school.schoolstat.models.entities.Matiere;
import com.school.schoolstat.services.interfaces.MatierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class MatiereServiceImpl implements MatierService {

    @Autowired
    private MatiereRepository matiereRepository;

    @Override
    public Matiere createMatiere(Matiere matiere) {
        return matiereRepository.save(matiere);
    }

    @Override
    public Matiere retrieveMatiere(Long id) {
        return matiereRepository.findById(id).get();
    }

    @Override
    public List<Matiere> retrieveMatiere() {
        return matiereRepository.findAll();
    }

    @Override
    public Matiere updateMatiere(Matiere matiere) {
        return matiereRepository.save(matiere);
    }

    @Override
    public void deleteMatiere(Matiere matiere) {
        matiereRepository.delete(matiere);
    }
}
