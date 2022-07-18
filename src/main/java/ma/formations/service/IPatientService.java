package ma.formations.service;

import ma.formations.service.model.Patient;
import ma.formations.service.model.RendezVous;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

/**
 * @author Hamza Ezzakri
 * @CreatedAt 6/27/2022 5:04 PM
 */


public interface IPatientService {

    void addPatient(Patient patient);
    boolean existsByDateVisiteAndHeureVisite(LocalDate dateVisite, LocalTime heureVisite);
    Patient findByCin(String cin);
    void addRendezVousToPatient(Patient patient);
    List<Patient> getAll();
    void updatePatient(Patient patient, Long idPatient);
    Patient getById(Long idPatient);
    boolean existsById(Long idPatient);
    void deletePatient(Long idPatient);
    void addFactureToPatient(Patient patient);
    Patient findByNomPrenom(String nomPrenom);
}
