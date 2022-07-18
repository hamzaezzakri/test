package ma.formations.service.model;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

/**
 * @author Hamza Ezzakri
 * @CreatedAt 6/1/2022 7:10 PM
 */

@Data
@Entity
@Table(name = "role")
public class Role implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "role_id")
    private int id;
    private String role;

    @Override
    public String getAuthority() {
        return role;
    }
}
