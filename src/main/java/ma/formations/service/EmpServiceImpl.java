package ma.formations.service;

import ma.formations.dao.EmpRepository;
import ma.formations.dto.EmpDTO;
import ma.formations.mapper.EmpMapper;
import ma.formations.service.model.Emp;
import ma.formations.service.model.Etat;
import ma.formations.service.model.Sexe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Hamza Ezzakri
 * @CreatedAt 6/2/2022 8:32 PM
 */

@Service
@Transactional
public class EmpServiceImpl implements IEmpService{

    @Autowired
    private EmpRepository empRepository;
    @Autowired
    private EmpMapper empMapper;

    @Override
    public List<EmpDTO> getEmployees() {

        return empMapper.toEmpDTO(empRepository.findAll()
                .stream()
                .filter(emp -> emp.isEnabled())
                .collect(Collectors.toList()));
    }

    @Override
    public EmpDTO convertToString(EmpDTO empDTO){

        if(empDTO.getEtat() != null) {
            if (empDTO.getEtat().equals(Etat.EN_ATTENTE.name())) {
                empDTO.setEtat(Etat.EN_ATTENTE.name().replace("_", " ").toLowerCase());
            } else {
                empDTO.setEtat(empDTO.getEtat().toLowerCase());
            }
        }
        return empDTO;
    }

    @Override
    public void save(EmpDTO empDTO) {

        Emp emp = empMapper.toEmp(empDTO);
        emp.setEtat(Etat.EN_ATTENTE);
        emp.setCreatedAt(LocalDateTime.now());
        emp.setEnabled(true);
        empRepository.save(emp);
    }

    @Override
    public void update(EmpDTO empDTO, long id){

        Emp oldEmp = empRepository.findById(id).get();
        empDTO.setId(oldEmp.getId());
        EmpDTO newEmpDTO = convertToEtat(empDTO);
        Emp newEmp = empMapper.toEmp(newEmpDTO);
        newEmp.setEnabled(oldEmp.isEnabled());
        newEmp.setCreatedAt(oldEmp.getCreatedAt());
        empRepository.save(newEmp);
    }

    @Override
    public EmpDTO convertToEtat(EmpDTO empDTO){

        if(empDTO.getEtat() != null) {
            if (empDTO.getEtat().equals("en attente")) {
                empDTO.setEtat(Etat.EN_ATTENTE.name());
            } else {
                empDTO.setEtat(empDTO.getEtat().toUpperCase());
            }
        }
        else{
            empDTO.setEtat(Etat.EN_ATTENTE.name());
        }
        return empDTO;
    }

    @Override
    public EmpDTO getEmpById(Long id) {

        boolean found = empRepository.existsById(id);
        if(!found)
            return null;

        return empMapper.toEmpDTO(empRepository.findById(id).get());
    }

    @Override
    public void delete(Long id) {

        empRepository.deleteById(id);
    }

    @Override
    public List<EmpDTO> findBySalary(Double salary) {

        return empMapper.toEmpDTO(empRepository.findBySalary(salary));
    }

    @Override
    public List<EmpDTO> findByFonction(String fonction) {

        return empMapper.toEmpDTO(empRepository.findByFonction(fonction));
    }

    @Override
    public List<EmpDTO> findBySalaryAndFonction(Double salary, String fonction) {

        return empMapper.toEmpDTO(empRepository.findBySalaryAndFonction(salary,fonction));
    }

    @Override
    public EmpDTO getEmpHavaingMaxSalary() {

        return empMapper.toEmpDTO(empRepository.getEmpHavaingMaxSalary());
    }

    @Override
    public List<EmpDTO> findAll(int pageId, int size) {

        return empMapper.toEmpDTO(empRepository.findAll(PageRequest.of(pageId,size, Sort.Direction.ASC,"name"))
                .getContent());
    }

    @Override
    public List<EmpDTO> sortBy(String fieldName) {

        return empMapper.toEmpDTO(empRepository.findAll(Sort.by(fieldName)));
    }

    @Override
    public EmpDTO convertSexe(EmpDTO empDTO) {
        return null;
    }
}
