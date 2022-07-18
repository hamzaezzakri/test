package ma.formations.mapper;

import ma.formations.dto.ConsultationDTO;
import ma.formations.dto.OrdonnanceDTO;
import ma.formations.dto.PatientDTO;
import ma.formations.dto.RendezVousDTO;
import ma.formations.service.model.Consultation;
import ma.formations.service.model.Ordonnance;
import ma.formations.service.model.Patient;
import ma.formations.service.model.RendezVous;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

/**
 * @author Hamza Ezzakri
 * @CreatedAt 6/25/2022 11:17 PM
 */

@Mapper(componentModel = "spring")
public interface RendezVousMapper {

    RendezVousDTO toRendezVousDTO(RendezVous rendezVous);
    @Mapping(target = "patient", ignore = true)
    @Mapping(target = "consultation", ignore = true)
    RendezVous toRendezVous(RendezVousDTO rendezVousDTO);
    List<RendezVousDTO> toRendezVousDTO(List<RendezVous> rendezVous);
    List<RendezVous> toRendezVous(List<RendezVousDTO> rendezVousDTOS);
    @Mapping(target = "rendezVous", ignore = true)
    @Mapping(target = "factures", ignore = true)
    PatientDTO toPatientDTO(Patient patient);
    @Mapping(target = "rendezVous", ignore = true)
    ConsultationDTO toConsultationDTO(Consultation consultation);
    @Mapping(target = "consultation", ignore = true)
    OrdonnanceDTO toOrdonnanceDTO(Ordonnance ordonnance);
}
