package ma.formations.service;

import ma.formations.service.model.RendezVous;

import java.util.List;

/**
 * @author Hamza Ezzakri
 * @CreatedAt 6/28/2022 6:21 PM
 */

public interface IRendezVousService {

    List<RendezVous> getAll();
    void addConsultationToRendezVous(RendezVous rendezVous);
    void updateRendezVous(RendezVous rendezVous, Long idRendezVous);
    RendezVous getById(Long idRendezVous);
    boolean existsById(Long idRendezVous);
    void deleteRendezVous(Long idRendezVous);
}
