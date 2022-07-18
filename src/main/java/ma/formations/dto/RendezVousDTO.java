package ma.formations.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * @author Hamza Ezzakri
 * @CreatedAt 6/25/2022 11:10 PM
 */

@Data
public class RendezVousDTO {

    private Long id;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateVisite;
    @JsonFormat(pattern = "HH:mm")
    private LocalTime heureVisite;
    private String etat;
    private String description;
    private LocalDateTime createdAt;
    private Boolean isEnabled;
    @JsonIgnoreProperties({"rendezVous","factures"})
    private PatientDTO patient;
    @JsonIgnoreProperties("rendezVous")
    private ConsultationDTO consultation;
}
