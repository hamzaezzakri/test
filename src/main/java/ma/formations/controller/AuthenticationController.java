package ma.formations.controller;

import ma.formations.dto.RoleDTO;
import ma.formations.dto.UserDTO;
import ma.formations.jwt.JwtUtils;
import ma.formations.service.IUserService;
import ma.formations.service.exception.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.validation.Valid;
import java.io.UnsupportedEncodingException;

/**
 * @author Hamza Ezzakri
 * @CreatedAt 6/3/2022 7:37 PM
 */

@CrossOrigin(origins = "*",maxAge = 3600)
@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private IUserService userService;
    @Autowired
    private JwtUtils jwtUtils;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody UserDTO userDTO){

        try {

            Authentication authentication = authenticate(userDTO);
            String jwt = jwtUtils.generateJwtToken(authentication);

            /*TokenDTO tokenDTO = new TokenDTO();
            tokenDTO.setJwttoken(jwt);
            tokenDTO.setUsername(userDTO.getUsername());
            authentication.getAuthorities().forEach(authority -> tokenDTO.getRoles().add(authority.getAuthority()));*/

            return ResponseEntity.ok(jwt);
        } catch (Exception e) {
            throw new BusinessException("login ou mot de passe incorrect");
        }
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody UserDTO userDTO) throws MessagingException, UnsupportedEncodingException {

        if(userService.existsByUsername(userDTO.getUsername()))
            return ResponseEntity.badRequest().body("Error: Username is already taken! ");

        //par défaut, l'utilisateur a le rôle client
        userDTO.getRoles().add(new RoleDTO("CLIENT"));
        userService.save(userDTO);

        Authentication authentication = authenticate(userDTO);

        String jwt = jwtUtils.generateJwtToken(authentication);

        /*TokenDTO tokenDTO = new TokenDTO();
        tokenDTO.setJwttoken(jwt);
        tokenDTO.setUsername(userDTO.getUsername());
        authentication.getAuthorities().forEach(authority -> tokenDTO.getRoles().add(authority.getAuthority()));*/

        userService.sendEmail(userDTO.getUsername());
        return ResponseEntity.ok(jwt);
    }

    private Authentication authenticate(UserDTO userDTO){

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(userDTO.getUsername(),userDTO.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        return authentication;
    }

}
