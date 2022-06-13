package ma.formations.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Hamza Ezzakri
 * @CreatedAt 6/1/2022 7:38 PM
 */

@Data
@NoArgsConstructor
public class UserDTO {

    private Long id;
    @Length(min = 5, message = "*Your username must have at least 5 characters")
    @NotEmpty(message = "*Please provide an user name")
    private String username;
    @Length(min = 5, message = "*Your password must have at least 5 characters")
    @NotEmpty(message = "*Please provide your password")
    private String password;
    private List<RoleDTO> roles = new ArrayList<>();

    public UserDTO(String username, String password, List<RoleDTO> roles) {
        this.username = username;
        this.password = password;
        this.roles = roles;
    }
}
