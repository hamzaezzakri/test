package ma.formations.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Hamza Ezzakri
 * @CreatedAt 6/1/2022 7:37 PM
 */

@Data
@NoArgsConstructor
public class RoleDTO {

    private int id;
    private String role;

    public RoleDTO(String role) {

        this.role = role;
    }
}
