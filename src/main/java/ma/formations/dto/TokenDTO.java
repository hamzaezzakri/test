package ma.formations.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Hamza Ezzakri
 * @CreatedAt 6/1/2022 9:02 PM
 */

@Getter
@Setter
public class TokenDTO implements Serializable{

    private String username;
    private String jwttoken;
    private List<String> roles = new ArrayList<>();
}
