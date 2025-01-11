package vpunko.spotify.security.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

    @PostMapping("/hello")//to proceed successForwardUrl
    public String hello() {
        return "Hello world";
    }

    @GetMapping("/home")
    public String home() {
        return "Home";
    }
}
