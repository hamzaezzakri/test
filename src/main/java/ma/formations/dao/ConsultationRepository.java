package ma.formations.dao;

import ma.formations.service.model.Consultation;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Hamza Ezzakri
 * @CreatedAt 6/24/2022 6:34 PM
 */

public interface ConsultationRepository extends JpaRepository<Consultation,Long> {
}
