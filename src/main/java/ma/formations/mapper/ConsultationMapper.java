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
 * @CreatedAt 6/26/2022 5:51 PM
 */

@Mapper(componentModel = "spring")
public interface ConsultationMapper {

    ConsultationDTO toConsultationDTO(Consultation consultation);
    @Mapping(target = "rendezVous", ignore = true)
    @Mapping(target = "ordonnance", ignore = true)
    Consultation toConsultation(ConsultationDTO consultationDTO);
    List<ConsultationDTO> toConsultationDTO(List<Consultation> consultations);
    List<Consultation> toConsultation(List<ConsultationDTO> consultationDTOS);
    @Mapping(target = "consultation", ignore = true)
    RendezVousDTO toRendezVousDTO(RendezVous rendezVous);
    @Mapping(target = "rendezVous", ignore = true)
    @Mapping(target = "factures", ignore = true)
    PatientDTO toPatientDTO(Patient patient);
    @Mapping(target = "consultation", ignore = true)
    OrdonnanceDTO toOrdonnanceDTO(Ordonnance ordonnance);
}
