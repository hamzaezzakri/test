package ma.formations.service;

import ma.formations.dao.FactureRepository;
import ma.formations.service.model.Facture;
import ma.formations.service.model.Ordonnance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Hamza Ezzakri
 * @CreatedAt 6/29/2022 2:20 PM
 */

@Service
public class FactureServiceImpl implements IFactureService{

    @Autowired
    private FactureRepository factureRepository;

    @Override
    public List<Facture> getAll() {

        return factureRepository.findAll();
    }

    @Override
    public void updateFacture(Facture facture, Long idFacture) {

        Facture oldFacture = factureRepository.findById(idFacture).get();
        facture.setId(oldFacture.getId());
        facture.setCreatedAt(oldFacture.getCreatedAt());
        facture.setIsEnabled(oldFacture.getIsEnabled());
        facture.setPatient(oldFacture.getPatient());
        factureRepository.save(facture);
    }

    @Override
    public void deleteFacture(Long idFacture) {

        Facture facture = factureRepository.findById(idFacture).get();
        facture.setIsEnabled(false);
        factureRepository.save(facture);
    }

    @Override
    public boolean existsById(Long idFacture) {

        return factureRepository.existsById(idFacture);
    }
}
