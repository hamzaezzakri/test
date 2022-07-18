package ma.formations.dao;

import ma.formations.service.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Hamza Ezzakri
 * @CreatedAt 6/20/2022 6:47 PM
 */

public interface PatientRepository extends JpaRepository<Patient,Long> {

    Patient findByNomPrenom(String nomPrenom);
    Patient findByCin(String cin);
}
