package ca.aec2018.project;

import ca.aec2018.project.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;


@EnableAutoConfiguration
@Controller
public class ProjectController {
    @Autowired
    WindRepository windRepository;

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

            s = new Solar(coord, Arrays.asList(dataPoints), annualAverage);
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

            w = new Wind(coord, Arrays.asList(dataPoints), annualAverage);
            windRepository.save(w);

            System.out.println("Saved Wind Object: " + w.getId());
        }

        windScanner.close();
        solarScanner.close();

        return "index";
    }

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
