package ca.aec2018.project;

import ca.aec2018.project.model.RenewableService;
import ca.aec2018.project.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;


@EnableAutoConfiguration
@Controller
public class ProjectController {

    @Autowired
    RenewableService renewableService;

    @Autowired
    CoordinateUtil coordinateUtil;

    /**
     * The controller method for the index page.
     * @return The twig file name.
     */
    @GetMapping("/")
    public String index() {
        return "index";
    }

    /**
     * The controller method for the solar page.
     * @return The twig file name.
     */
    @GetMapping("/solar")
    public String solar() {
        return "solar";
    }

    /**
     * The controller method for the wind page.
     * @return The twig file name.
     */
    @GetMapping("/wind")
    public String wind() {
        return "wind";
    }

    /**
     * The controller method for the documentation page.
     * @return The twig file name.
     */
    @GetMapping("/docs")
    public String docs() {
        return "docs";
    }

    /**
     * The REST controller method to get the wind data.
     * @return The Google JSON objects to render the wind heat map.
     */
    @GetMapping("/wind/{month}")
    @ResponseBody
    public List<GoogleRenewable> getWindData(@PathVariable int month) {
        return renewableService.allRenewableToGoogleRenewable(false, month);
    }

    /**
     * The REST controller method to get the solar data.
     * @return The Google JSON objects to render the solar heat map.
     */
    @GetMapping("/solar/{month}")
    @ResponseBody
    public List<GoogleRenewable> getSolarData(@PathVariable int month) {
        return renewableService.allRenewableToGoogleRenewable(false, month);
    }

    /**
     * The REST controller method to get the wind data for a specific coordinate.
     * @return The Google JSON objects to render the wind heat map.
     */
    @GetMapping("/wind/coordinate")
    @ResponseBody
    public List<GoogleRenewable> getWindDataForCoordinate(@RequestBody List<Integer> coordinate) {
        return null;
    }

    /**
     * The REST controller method to get the solar data for a specific coordinate.
     * @return The Google JSON objects to render the solar heat map.
     */
    @GetMapping("/solar/coordinate")
    @ResponseBody
    public List<GoogleRenewable> getSolarDataForCoordinate(@RequestBody List<Integer> coordinate) {
        return null;
    }

    /**
     * The REST controller method to get the wind data for a region enclosed within a rectangle created by two coordinates.
     * @return The Google JSON objects to render the wind heat map.
     */
    @GetMapping("/wind/coordinates")
    @ResponseBody
    public List<GoogleRenewable> getWindDataForRegion(@RequestBody List<Integer> coordinateX, @RequestBody List<Integer> coordinateY) {
        return null;
    }

    /**
     * The REST controller method to get the solar data for a region enclosed within a rectangle created by two coordinates.
     * @return The Google JSON objects to render the solar heat map.
     */
    @GetMapping("/solar/coordinates")
    @ResponseBody
    public List<GoogleRenewable> getSolarDataForRegion(@RequestBody List<Integer> coordinateX, @RequestBody List<Integer> coordinateY) {
        return null;
    }
}
