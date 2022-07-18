package ma.formations.service.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * @author Hamza Ezzakri
 * @CreatedAt 6/24/2022 6:10 PM
 */

@NoArgsConstructor
@Data
@Entity
@Table(name = "consultation")
public class Consultation {

    @Id
    @GeneratedValue
    private Long id;
    private String description;
    private LocalDateTime createdAt;
    private Boolean isEnabled;
    @JsonIgnoreProperties("consultation")
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "rendezVous_id")
    private RendezVous rendezVous;
    @JsonIgnoreProperties("consultation")
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "consultation")
    private Ordonnance ordonnance;

    public Consultation(String description, LocalDateTime createdAt, Boolean isEnabled) {
        this.description = description;
        this.createdAt = createdAt;
        this.isEnabled = isEnabled;
    }
}
