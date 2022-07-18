package ma.formations.mapper;

import ma.formations.dto.RoleDTO;
import ma.formations.service.model.Role;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * @author Hamza Ezzakri
 * @CreatedAt 6/25/2022 11:04 PM
 */

@Mapper(componentModel = "spring")
public interface RoleMapper {

    RoleDTO toRoleDTO(Role role);
    Role toRole(RoleDTO roleDTO);
    List<RoleDTO> toRoleDTO(List<Role> roles);
    List<Role> toRole(List<RoleDTO> roleDTOS);
}

