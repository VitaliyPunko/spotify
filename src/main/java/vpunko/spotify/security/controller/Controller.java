package vpunko.spotify.security.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

    @GetMapping("/hello")
    public String hello() {
        return "Hello world";
    }

    @GetMapping("/home")
    public String home() {
        return "Home";
    }
}
