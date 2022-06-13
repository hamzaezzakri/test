package ma.formations.service;

import ma.formations.dao.EmpRepository;
import ma.formations.dao.RoleRepository;
import ma.formations.dao.UserRepository;
import ma.formations.dto.RoleDTO;
import ma.formations.dto.UserDTO;
import ma.formations.mapper.RoleMapper;
import ma.formations.mapper.UserMapper;
import ma.formations.service.exception.BusinessException;
import ma.formations.service.model.Role;
import ma.formations.service.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Hamza Ezzakri
 * @CreatedAt 6/2/2022 7:35 PM
 */

@Service
@Transactional
public class UserServiceImpl implements IUserService{

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private EmpRepository empRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private JavaMailSender mailSender;
    @Value("${app.email}")
    private String from;
    @Value("${app.personal}")
    private String personal;

    @Override
    public UserDTO save(UserDTO userDTO) {

        User user = userMapper.toUser(userDTO);
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        List<Role> rolesPersist = new ArrayList<>();
        for (Role role : user.getRoles()) {
            Role userRole = roleRepository.findByRole(role.getRole()).get(0);
            rolesPersist.add(userRole);
        }
        user.setRoles(rolesPersist);
        return userMapper.toUserDTO(userRepository.save(user));
    }

    @Override
    public void save(RoleDTO roleDTO) {

        roleRepository.save(roleMapper.toRole(roleDTO));
    }

    @Override
    public List<UserDTO> getAllUsers() {

        return userMapper.toUserDTO(userRepository.findAll());
    }

    @Override
    public List<RoleDTO> getAllRoles() {

        return roleMapper.toRoleDTO(roleRepository.findAll());
    }

    @Override
    public RoleDTO getRoleByName(String role) {

        return roleMapper.toRoleDTO(roleRepository.findByRole(role).get(0));
    }

    @Override
    public void cleanDataBase() {

        userRepository.deleteAll();
        roleRepository.deleteAll();
        empRepository.deleteAll();
    }

    @Override
    public boolean existsByUsername(String username) {

        return userRepository.existsByUsername(username);
    }

    @Override
    public boolean existsByRole(String role) {

        return roleRepository.existsByRole(role);
    }

    @Override
    public UserDTO addRoleToUser(String username, String role) {

        if(!userRepository.existsByUsername(username) || !roleRepository.existsByRole(role))
            throw new BusinessException("the provided username or role does not exist, please enter valid data!!!");

        User user = userRepository.findByUsername(username);
        Role newRole = roleRepository.findByRole(role).get(0);
        user.getRoles().add(newRole);
        userRepository.save(user);
        return userMapper.toUserDTO(user);
    }

    @Override
    public UserDTO findByUsername(String username) {

        if (username == null || username.trim().equals(""))
            throw new BusinessException("login is empty !!");

        User user = userRepository.findByUsername(username);

        if (user == null)
            throw new BusinessException("No user with this login");

        return userMapper.toUserDTO(user);
    }

    @Override
    @Async
    public void sendEmail(String to) throws MessagingException, UnsupportedEncodingException {

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message,"utf-8");

        String content = "<p> <b>Welcome to our Application</b> </p>" +
                "<p style=\"font-family:Helvetica,Arial,sans-serif;font-size:19px;\">Thank you for registering <span style=\"color:#1D70B8\">we're so excited</span> </p>";
        helper.setFrom(from,personal);
        helper.setTo(to);
        helper.setSubject("Welcome to our Application");
        helper.setText(content,true);
        mailSender.send(message);
    }
}
