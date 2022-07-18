package ma.formations.oauth2;

import lombok.extern.slf4j.Slf4j;
import ma.formations.dao.UserRepository;
import ma.formations.jwt.JwtUtils;
import ma.formations.service.model.Role;
import ma.formations.service.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Hamza Ezzakri
 * @CreatedAt 7/13/2022 7:41 PM
 */

@Slf4j
@Component
public class OAuth2LoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    @Autowired private UserRepository userRepository;
    @Autowired private JwtUtils jwtUtils;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

        OAuth2UserImpl oAuth2User = (OAuth2UserImpl) authentication.getPrincipal();

        User user = new User();
        user.setEmail(oAuth2User.getEmail());
        user.setUsername(oAuth2User.getFullName());
        user.setRoles(getAuthorities(oAuth2User.getAuthorities()));

        userRepository.save(user);

        System.out.println("customer's email : " + oAuth2User.getEmail());
        System.out.println("customer's roles : " + oAuth2User.getAuthorities()
                .stream()
                .filter(auth -> auth.getAuthority().contains("ROLE"))
                .map(role -> role.getAuthority().replace("ROLE_",""))
                .collect(Collectors.toList()));

        String jwt = jwtUtils.generateOAuth2JwtToken(authentication);
        log.info("JWT token : {}", jwt);
        response.getWriter().append("Bearer " + jwt);
    }

    private List<Role> getAuthorities(Collection<? extends GrantedAuthority> roles){

        return roles.stream()
                .filter(auth -> auth.getAuthority().contains("ROLE"))
                .map(role -> {
                    Role newRole = new Role();
                    newRole.setRole(role.getAuthority().replace("ROLE_",""));
                    return newRole;
                }).collect(Collectors.toList());
    }


}
