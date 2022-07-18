package ma.formations.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Hamza Ezzakri
 * @CreatedAt 6/25/2022 11:10 PM
 */

@Data
public class PatientDTO {

    private Long id;
    private String cin;
    private String nomPrenom;
    private String sexe;
    private LocalDate dateNaissance;
    private String email;
    private String adresse;
    private String telephone;
    private LocalDateTime createdAt;
    private Boolean isEnabled;
    @JsonIgnoreProperties("patient")
    List<RendezVousDTO> rendezVous = new ArrayList<>();
    @JsonIgnoreProperties("patient")
    List<FactureDTO> factures = new ArrayList<>();
}
