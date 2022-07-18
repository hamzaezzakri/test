package ma.formations.service.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * @author Hamza Ezzakri
 * @CreatedAt 6/1/2022 7:27 PM
 */


@Data
@Entity
@Table(name = "emp")
public class Emp {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private Double salary;
    private String fonction;
    @Enumerated(EnumType.STRING)
    private Etat etat;
    private LocalDateTime createdAt;
    private boolean isEnabled;
}
