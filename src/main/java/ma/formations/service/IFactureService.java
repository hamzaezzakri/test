package ma.formations.service;

import ma.formations.service.model.Facture;
import ma.formations.service.model.Ordonnance;

import java.util.List;

/**
 * @author Hamza Ezzakri
 * @CreatedAt 6/29/2022 2:20 PM
 */

public interface IFactureService {

    List<Facture> getAll();
    void updateFacture(Facture facture, Long idFacture);
    boolean existsById(Long idFacture);
    void deleteFacture(Long idFacture);
}
