package ma.formations.mapper;

import ma.formations.dto.UserDTO;
import ma.formations.service.model.User;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * @author Hamza Ezzakri
 * @CreatedAt 6/25/2022 11:03 PM
 */

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDTO toUserDTO(User user);
    User toUser(UserDTO userDTO);
    List<UserDTO> toUserDTO(List<User> users);
    List<User> toUser(List<UserDTO> userDTOS);
}
