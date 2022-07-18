package ma.formations.mapper;

import ma.formations.dto.FactureDTO;
import ma.formations.dto.PatientDTO;
import ma.formations.service.model.Facture;
import ma.formations.service.model.Patient;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

/**
 * @author Hamza Ezzakri
 * @CreatedAt 6/26/2022 5:43 PM
 */

@Mapper(componentModel = "spring")
public interface FactureMapper {

    FactureDTO toFactureDTO(Facture facture);
    @Mapping(target = "patient", ignore = true)
    Facture toFacture(FactureDTO factureDTO);
    List<FactureDTO> toFactureDTO(List<Facture> factures);
    List<Facture> toFacture(List<FactureDTO> factureDTOS);
    @Mapping(target = "rendezVous", ignore = true)
    @Mapping(target = "factures", ignore = true)
    PatientDTO toPatientDTO(Patient patient);
}
