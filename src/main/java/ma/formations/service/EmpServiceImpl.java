package ma.formations.service;

import ma.formations.dao.EmpRepository;
import ma.formations.dto.EmpDTO;
import ma.formations.mapper.EmpMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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

        return empMapper.toEmpDTO(empRepository.findAll());
    }

    @Override
    public void save(EmpDTO empDTO) {

        empRepository.save(empMapper.toEmp(empDTO));
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
}
