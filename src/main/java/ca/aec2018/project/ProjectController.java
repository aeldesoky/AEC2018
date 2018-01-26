package ca.aec2018.project;

import ca.aec2018.project.model.RenewableService;
import ca.aec2018.project.model.WindRepository;
import ca.aec2018.project.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


@EnableAutoConfiguration
@Controller
public class ProjectController {
    @Autowired
    WindRepository windRepository;

    @Autowired
    RenewableService renewableService;

    @Autowired
    SolarRepository solarRepository;

    @Autowired
    CoordinateRepository coordinateRepository;

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

    @GetMapping("/docs")
    public String docs() {
        return "docs";
    }

    @GetMapping("/wind/{month}")
    @ResponseBody
    public List<GoogleRenewable> getWindData(@PathVariable int month) {
        return renewableService.allRenewableToGoogleRenewable(false, month);
    }

    @GetMapping("/solar/{month}")
    @ResponseBody
    public List<GoogleRenewable> getSolarData(@PathVariable int month) {
        return renewableService.allRenewableToGoogleRenewable(false, month);
    }
}
