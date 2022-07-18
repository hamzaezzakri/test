package ma.formations.service;

import ma.formations.service.model.Consultation;
import ma.formations.service.model.RendezVous;

import java.util.List;

/**
 * @author Hamza Ezzakri
 * @CreatedAt 6/29/2022 2:17 PM
 */

public interface IConsultationService {

    List<Consultation> getAll();
    void addOrdonnanceToConsultation(Consultation consultation);
    void updateConsultation(Consultation consultation, Long idConsultation);
    Consultation getById(Long idConsultation);
    boolean existsById(Long idConsultation);
    void deleteConsultation(Long idConsultation);
}
