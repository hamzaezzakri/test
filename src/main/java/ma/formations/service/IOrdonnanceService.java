package ma.formations.service;

import ma.formations.service.model.Ordonnance;

import java.util.List;

/**
 * @author Hamza Ezzakri
 * @CreatedAt 6/29/2022 2:19 PM
 */

public interface IOrdonnanceService {

    List<Ordonnance> getAll();
    void updateOrdonnance(Ordonnance ordonnance, Long idOrdonnance);
    boolean existsById(Long idOrdonnance);
    void deleteOrdonnance(Long idOrdonnance);
}
