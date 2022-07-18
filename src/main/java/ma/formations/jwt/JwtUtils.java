package ma.formations.jwt;

import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import ma.formations.oauth2.OAuth2UserImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author Hamza Ezzakri
 * @CreatedAt 6/2/2022 9:05 PM
 */

@Slf4j
@Component
public class JwtUtils {

    //Spring EL (Expression Language)
    @Value("${app.jwtSecret}")
    private String jwtSecret;
    @Value("${app.jwtExpirationMs}")
    private int jwtExpirationMs;

    public String generateJwtToken(Authentication authentication){

        UserDetails userPrincipal = (UserDetails) authentication.getPrincipal();

        return Jwts.builder()
                .setSubject(userPrincipal.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + jwtExpirationMs))
                .claim("roles",userPrincipal.getAuthorities())
                .signWith(SignatureAlgorithm.HS512,jwtSecret)
                .compact();
    }

    public String generateOAuth2JwtToken(Authentication authentication){

        OAuth2UserImpl oAuth2User = (OAuth2UserImpl) authentication.getPrincipal();
        return Jwts.builder()
                .setSubject(oAuth2User.getFullName())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + jwtExpirationMs))
                .claim("roles","USER")
                .signWith(SignatureAlgorithm.HS512,jwtSecret)
                .compact();
    }

    public String getUsernameFromJwtToken(String token){

        return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().getSubject();
    }

    public boolean validateJwtToken(String token){

        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
            return true;
        } catch (ExpiredJwtException e) {
            log.error("JWT token is expired: {}",e.getMessage());
        } catch (UnsupportedJwtException e) {
            log.error("JWT token is unsupported: {}",e.getMessage());
        } catch (MalformedJwtException e) {
            log.error("invalid JWT token: {}",e.getMessage());
        } catch (SignatureException e) {
            log.error("invalid JWt signature: {}",e.getMessage());
        } catch (IllegalArgumentException e) {
            log.error("JWT claims string is empty: {}",e.getMessage());
        }
        return false;
    }
}
