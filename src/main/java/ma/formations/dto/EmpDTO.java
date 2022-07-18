package ma.formations.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Hamza Ezzakri
 * @CreatedAt 6/25/2022 10:54 PM
 */

@Data
@NoArgsConstructor
public class EmpDTO {

    private Long id;
    private String name;
    private Double salary;
    private String fonction;
    private String etat;

    public EmpDTO(String name, Double salary, String fonction) {
        this.name = name;
        this.salary = salary;
        this.fonction = fonction;
    }
}
