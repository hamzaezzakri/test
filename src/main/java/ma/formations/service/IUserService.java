package ma.formations.service;

import ma.formations.dto.RoleDTO;
import ma.formations.dto.UserDTO;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * @author Hamza Ezzakri
 * @CreatedAt 6/2/2022 7:30 PM
 */

public interface IUserService{

    UserDTO save(UserDTO userDTO);
    void save(RoleDTO roleDTO);
    List<UserDTO> getAllUsers();
    List<RoleDTO> getAllRoles();
    RoleDTO getRoleByName(String role);
    void cleanDataBase();
    boolean existsByUsername(String username);
    boolean existsByRole(String role);
    UserDTO addRoleToUser(String username, String role);
    UserDTO findByUsername(String username);
    void sendEmail(String to) throws MessagingException, UnsupportedEncodingException;
}
