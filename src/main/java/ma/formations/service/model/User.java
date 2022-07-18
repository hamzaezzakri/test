package ma.formations.service.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Hamza Ezzakri
 * @CreatedAt 6/1/2022 7:14 PM
 */

@Data
@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue
    private Long id;
    @Email
    private String email;
    @Length(min = 5, message = "le nom d'utilisateur doit contenir au moins 5 caractères")
    @NotEmpty(message = "le nom d'utilisateur ne peut pas être vide")
    //@Column(unique = true)
    private String username;
    //@Length(min = 5, message = "le mot de passe doit contenir au moins 5 caractères")
    //@NotEmpty(message = "le mot de passe ne peut pas être vide")
    private String password;
    //@NotNull
    //@Future(message = "la date de visite doit être une date dans le futur")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm")
    private LocalDateTime endDate;
    @Enumerated(EnumType.STRING)
    private Sexe sexe;
    @ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinTable(name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private List<Role> roles = new ArrayList<>();

    public String getFullName() {
        return username;
    }
}
