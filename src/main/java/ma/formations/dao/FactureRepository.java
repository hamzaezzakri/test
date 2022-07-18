package ma.formations.dao;

import ma.formations.service.model.Facture;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Hamza Ezzakri
 * @CreatedAt 6/24/2022 6:44 PM
 */

public interface FactureRepository extends JpaRepository<Facture,Long> {
}
