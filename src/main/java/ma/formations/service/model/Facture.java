package ma.formations.service.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * @author Hamza Ezzakri
 * @CreatedAt 6/24/2022 4:23 PM
 */

@NoArgsConstructor
@Data
@Entity
@Table(name = "facture")
public class Facture {

    @Id
    @GeneratedValue
    private Long id;
    private Double montant;
    private LocalDateTime createdAt;
    private Boolean isEnabled;
    @JsonIgnoreProperties({"factures","rendezVous"})
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "patient_id")
    private Patient patient;

    public Facture(double montant, LocalDateTime createdAt, boolean isEnabled) {
        this.montant = montant;
        this.createdAt = createdAt;
        this.isEnabled = isEnabled;
    }
}
