package ma.formations.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


/**
 * @author Hamza Ezzakri
 * @CreatedAt 7/13/2022 6:16 PM
 */

@Controller
public class HomeController {

    @GetMapping("/")
    public String home(){
        return "index";
    }
}
