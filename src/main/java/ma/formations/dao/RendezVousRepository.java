package ma.formations.dao;

import ma.formations.service.model.Etat;
import ma.formations.service.model.RendezVous;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

/**
 * @author Hamza Ezzakri
 * @CreatedAt 6/20/2022 6:47 PM
 */

public interface RendezVousRepository extends JpaRepository<RendezVous,Long> {

    RendezVous findByEtat(Etat etat);
    RendezVous findByDateVisiteAndHeureVisite(LocalDate dateVisite, LocalTime heureVisite);
    boolean existsByDateVisiteAndHeureVisite(LocalDate dateVisite, LocalTime heureVisite);
}
