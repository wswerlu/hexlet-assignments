package exercise.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

// BEGIN
import exercise.daytime.Daytime;
import org.springframework.beans.factory.annotation.Autowired;

@RestController
public class WelcomeController {

    @Autowired
    private Daytime daytime;

    @GetMapping(path = "/welcome")
    public String welcome() {
        String currentTime = daytime.getName();

        return "It is " + currentTime + " now! Welcome to Spring!";
    }
}
// END
