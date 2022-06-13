package ma.formations.mapper;

import ma.formations.dto.EmpDTO;
import ma.formations.service.model.Emp;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * @author Hamza Ezzakri
 * @CreatedAt 6/11/2022 1:10 PM
 */

@Mapper(componentModel = "spring")
public interface EmpMapper {

    EmpDTO toEmpDTO(Emp emp);
    Emp toEmp(EmpDTO empDTO);
    List<EmpDTO> toEmpDTO(List<Emp> emps);
    List<Emp> toEmp(List<EmpDTO> empDTOS);
}
