package ma.formations.service.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * @author Hamza Ezzakri
 * @CreatedAt 6/24/2022 7:05 PM
 */

@NoArgsConstructor
@Data
@Entity
@Table(name = "ordonnance")
public class Ordonnance {

    @Id
    @GeneratedValue
    private Long id;
    private String description;
    private LocalDateTime createdAt;
    private Boolean isEnabled;
    @JsonIgnoreProperties("ordonnance")
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "consultation_id")
    private Consultation consultation;

    public Ordonnance(String description, LocalDateTime createdAt, Boolean isEnabled) {
        this.description = description;
        this.createdAt = createdAt;
        this.isEnabled = isEnabled;
    }
}
