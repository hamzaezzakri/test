package ma.formations.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @author Hamza Ezzakri
 * @CreatedAt 6/25/2022 11:15 PM
 */

@Data
public class FactureDTO {

    private Long id;
    private Double montant;
    private LocalDateTime createdAt;
    private Boolean isEnabled;
    @JsonIgnoreProperties({"factures","rendezVous"})
    private PatientDTO patient;
}
