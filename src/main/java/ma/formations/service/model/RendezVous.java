package ma.formations.service.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * @author Hamza Ezzakri
 * @CreatedAt 6/20/2022 5:57 PM
 */

@NoArgsConstructor
@Data
@Entity
@Table(name = "rendezVous")
public class RendezVous {

    @Id
    @GeneratedValue
    private Long id;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateVisite;
    @JsonFormat(pattern = "HH:mm")
    private LocalTime heureVisite;
    @Enumerated(EnumType.STRING)
    private Etat etat;
    private String description;
    private LocalDateTime createdAt;
    private Boolean isEnabled;
    @JsonIgnoreProperties({"rendezVous","factures"})
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "patient_id")
    private Patient patient;
    @JsonIgnoreProperties("rendezVous")
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "rendezVous")
    private Consultation consultation;

    public RendezVous(LocalDate dateVisite, LocalTime heureVisite, Etat etat, String description, LocalDateTime createdAt, boolean isEnabled) {
        this.dateVisite = dateVisite;
        this.heureVisite = heureVisite;
        this.etat = etat;
        this.description = description;
        this.createdAt = createdAt;
        this.isEnabled = isEnabled;
    }

}
