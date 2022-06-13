package ma.formations.service.exception;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Hamza Ezzakri
 * @CreatedAt 6/2/2022 7:26 PM
 */

@Getter
@Setter
public class BusinessException extends RuntimeException{

    private String message;

    public BusinessException(String message) {
        this.message = message;
    }
}
