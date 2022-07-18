package ma.formations.service;

import ma.formations.dto.EmpDTO;
import ma.formations.service.model.RendezVous;

import java.util.List;

/**
 * @author Hamza Ezzakri
 * @CreatedAt 6/2/2022 8:30 PM
 */

public interface IEmpService {

    List<EmpDTO> getEmployees();
    void save(EmpDTO empDTO);
    void update(EmpDTO empDTO,long id);
    EmpDTO getEmpById(Long id);
    void delete(Long id);
    List<EmpDTO> findBySalary(Double salary);
    List<EmpDTO> findByFonction(String fonction);
    List<EmpDTO> findBySalaryAndFonction(Double salary, String fonction);
    EmpDTO getEmpHavaingMaxSalary();
    //Pour la pagination
    List<EmpDTO> findAll(int pageId, int size);
    //pour le tri
    List<EmpDTO> sortBy(String fieldName);
    EmpDTO convertSexe(EmpDTO empDTO);
    EmpDTO convertToString(EmpDTO empDTO);
    EmpDTO convertToEtat(EmpDTO empDTO);
}
