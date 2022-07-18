package ma.formations.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @author Hamza Ezzakri
 * @CreatedAt 6/25/2022 11:14 PM
 */

@Data
public class OrdonnanceDTO {

    private Long id;
    private String description;
    private LocalDateTime createdAt;
    private Boolean isEnabled;
    @JsonIgnoreProperties("ordonnance")
    private ConsultationDTO consultation;
}
