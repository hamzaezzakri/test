package ma.formations.controller;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @author Hamza Ezzakri
 * @CreatedAt 6/6/2022 5:03 PM
 */

@Getter
@Setter
@AllArgsConstructor
public class ErrorResponse {

    private String message;
    private List<String> details;
}
