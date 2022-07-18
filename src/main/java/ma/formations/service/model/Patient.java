package ma.formations.service.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Hamza Ezzakri
 * @CreatedAt 6/20/2022 4:25 PM
 */


@NoArgsConstructor
@Data
@Entity
@Table(name = "patient")
public class Patient {

    @Id
    @GeneratedValue
    private Long id;
    @Column(unique = true)
    private String cin;
    private String nomPrenom;
    @Enumerated(EnumType.STRING)
    private Sexe sexe;
    private LocalDate dateNaissance;
    private String email;
    private String adresse;
    private String telephone;
    private LocalDateTime createdAt;
    private Boolean isEnabled;
    @JsonIgnoreProperties("patient")
    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "patient")
    List<RendezVous> rendezVous = new ArrayList<>();
    @JsonIgnoreProperties("patient")
    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "patient")
    List<Facture> factures = new ArrayList<>();

    public Patient(String cin, String nomPrenom, Sexe sexe, LocalDate dateNaissance, String email, String adresse, String telephone, LocalDateTime createdAt, boolean isEnabled, List<RendezVous> rendezVous) {
        this.cin = cin;
        this.nomPrenom = nomPrenom;
        this.sexe = sexe;
        this.dateNaissance = dateNaissance;
        this.email = email;
        this.adresse = adresse;
        this.telephone = telephone;
        this.createdAt = createdAt;
        this.isEnabled = isEnabled;
        this.rendezVous = rendezVous;
    }

}
