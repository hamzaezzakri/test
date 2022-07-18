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
 * @CreatedAt 6/26/2022 6:04 PM
 */

@Mapper(componentModel = "spring")
public interface OrdonnanceMapper {

    OrdonnanceDTO toOrdonnanceDTO(Ordonnance Ordonnance);
    @Mapping(target = "consultation", ignore = true)
    Ordonnance toOrdonnance(OrdonnanceDTO OrdonnanceDTO);
    List<OrdonnanceDTO> toOrdonnanceDTO(List<Ordonnance> Ordonnances);
    List<Ordonnance> toOrdonnance(List<OrdonnanceDTO> OrdonnanceDTOS);
    @Mapping(target = "ordonnance", ignore = true)
    ConsultationDTO toConsultationDTO(Consultation consultation);
    @Mapping(target = "consultation", ignore = true)
    RendezVousDTO toRendezVousDTO(RendezVous rendezVous);
    @Mapping(target = "rendezVous", ignore = true)
    @Mapping(target = "factures", ignore = true)
    PatientDTO toPatientDTO(Patient patient);
}
