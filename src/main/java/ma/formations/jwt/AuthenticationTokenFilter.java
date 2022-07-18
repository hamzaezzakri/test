package ma.formations.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.formations.service.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Hamza Ezzakri
 * @CreatedAt 6/6/2022 9:16 PM
 */

@Slf4j
@AllArgsConstructor
public class AuthenticationTokenFilter extends UsernamePasswordAuthenticationFilter {

    private AuthenticationManager authenticationManager;
    private JwtUtils jwtUtils;

    /*public AuthenticationTokenFilter(AuthenticationManager authenticationManager, JwtUtils jwtUtils) {
        this.authenticationManager = authenticationManager;
        this.jwtUtils = jwtUtils;
    }*/

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {

        User user;

        try {

            user = new ObjectMapper().readValue(request.getInputStream(),User.class);
            log.info("User : {}", user);

        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("une erreur s'est produite lors de la récupération de l'objet user");
        }

        return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(),user.getPassword()));
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException {

        String jwt = jwtUtils.generateJwtToken(authResult);
        log.info("JWT token : {}", jwt);
        response.getWriter().append("Bearer " + jwt);
    }
}
