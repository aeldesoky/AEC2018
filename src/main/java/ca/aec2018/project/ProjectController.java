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
import java.util.Arrays;
import java.util.List;
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

    @GetMapping("/parse")
    public String parse() throws FileNotFoundException {
        File solarFile = new File("src/main/resources/solarNASA.txt");
        File windFile = new File("src/main/resources/windNASA.txt");

        Scanner solarScanner = new Scanner(solarFile);
        Scanner windScanner = new Scanner(windFile);

        String[] line;
        Coordinate coord;
        Solar s;
        Wind w;
        Integer latitude;
        Integer longitude;
        Double[] dataPoints;
        Double annualAverage;

        System.out.println("===========Parsing Solar Data===========");
        while(solarScanner.hasNextLine()) {
            //Parse Solar Data
            line = solarScanner.nextLine().split(" ");
            latitude = Integer.parseInt(line[0]);
            longitude = Integer.parseInt(line[1]);

            dataPoints = new Double[12];
            for (int index = 0; index < 12; index++)
                dataPoints[index] = Double.parseDouble(line[2 + index]);

            annualAverage = Double.parseDouble(line[13]);

            coord = new Coordinate(latitude, longitude);

            try {
                coordinateRepository.save(coord);
            } catch(Exception e) {
                e.printStackTrace();
                coord = coordinateRepository.findByLatitudeAndLongitude(latitude, longitude);
            }

            s = new Solar();
            s.setAnnualAverage(annualAverage);
            s.setCoordinate(coord);
            s.setJanuary(Double.parseDouble(line[2]));
            s.setFebruary(Double.parseDouble(line[3]));
            s.setMarch(Double.parseDouble(line[4]));
            s.setApril(Double.parseDouble(line[5]));
            s.setMay(Double.parseDouble(line[6]));
            s.setJune(Double.parseDouble(line[7]));
            s.setJuly(Double.parseDouble(line[8]));
            s.setAugust(Double.parseDouble(line[9]));
            s.setSeptember(Double.parseDouble(line[10]));
            s.setOctober(Double.parseDouble(line[11]));
            s.setNovember(Double.parseDouble(line[12]));
            s.setDecember(Double.parseDouble(line[13]));
            solarRepository.save(s);

            System.out.println("Saved Solar Object: " + s.getId());
        }

        System.out.println("===========Parsing Wind Data===========");
        while(windScanner.hasNextLine()) {
            //Parse Wind Data
            line = windScanner.nextLine().split(" ");
            latitude = Integer.parseInt(line[0]);
            longitude = Integer.parseInt(line[1]);

            dataPoints = new Double[12];
            for (int index = 0; index < 12; index++)
                dataPoints[index] = Double.parseDouble(line[2 + index]);

            annualAverage = Double.parseDouble(line[13]);

            coord = new Coordinate(latitude, longitude);

            try {
                coordinateRepository.save(coord);
            } catch(Exception e) {
                e.printStackTrace();
                coord = coordinateRepository.findByLatitudeAndLongitude(latitude, longitude);
            }

            w = new Wind();
            w.setAnnualAverage(annualAverage);
            w.setCoordinate(coord);
            w.setJanuary(Double.parseDouble(line[2]));
            w.setFebruary(Double.parseDouble(line[3]));
            w.setMarch(Double.parseDouble(line[4]));
            w.setApril(Double.parseDouble(line[5]));
            w.setMay(Double.parseDouble(line[6]));
            w.setJune(Double.parseDouble(line[7]));
            w.setJuly(Double.parseDouble(line[8]));
            w.setAugust(Double.parseDouble(line[9]));
            w.setSeptember(Double.parseDouble(line[10]));
            w.setOctober(Double.parseDouble(line[11]));
            w.setNovember(Double.parseDouble(line[12]));
            w.setDecember(Double.parseDouble(line[13]));

            windRepository.save(w);

            System.out.println("Saved Wind Object: " + w.getId());
        }

        windScanner.close();
        solarScanner.close();

        return "index";
    }

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
        return "map";
    }

    /**
     * The controller method for the wind page.
     * @return The twig file name.
     */
    @GetMapping("/wind")
    public String wind() {
        return "map";
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
}
