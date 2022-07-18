package ma.formations.dao;

import ma.formations.service.model.Ordonnance;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Hamza Ezzakri
 * @CreatedAt 6/24/2022 7:42 PM
 */

public interface OrdonnanceRepository extends JpaRepository<Ordonnance,Long> {
}
