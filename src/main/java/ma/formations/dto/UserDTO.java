package ma.formations.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Hamza Ezzakri
 * @CreatedAt 6/25/2022 10:55 PM
 */

@Data
@NoArgsConstructor
public class UserDTO {

    private Long id;
    @Email
    private String email;
    @Length(min = 5, message = "*Your username must have at least 5 characters")
    @NotEmpty(message = "*Please provide an user name")
    private String username;
    //@Length(min = 5, message = "*Your password must have at least 5 characters")
    //@NotEmpty(message = "*Please provide your password")
    private String password;
    private List<RoleDTO> roles = new ArrayList<>();

    public UserDTO(String email, String username, String password, List<RoleDTO> roles) {
        this.email = email;
        this.username = username;
        this.password = password;
        this.roles = roles;
    }
}
