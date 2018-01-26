package ca.aec2018.project;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@EnableAutoConfiguration
@Controller
public class ProjectController {
    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/solar")
    public String solar() {
        return "map";
    }

    @GetMapping("/wind")
    public String wind() {
        return "map";
    }
}
