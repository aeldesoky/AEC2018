package ca.aec2018.project;

import ca.aec2018.project.model.*;

import java.io.FileNotFoundException;
import java.util.Scanner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;


@SpringBootApplication
@EnableAutoConfiguration
public class DataProcessingApplication {

    @Autowired
    WindRepository windRepository;

    @Autowired
    SolarRepository solarRepository;

    @Autowired
    CoordinateRepository coordinateRepository;

    public static void main(String[] args) {
        SpringApplication.run(DataProcessingApplication.class, args);
        try {
            new DataProcessingApplication();
        } catch(FileNotFoundException e) {
            System.out.println("File not found");
        }
    }

    public DataProcessingApplication() throws FileNotFoundException {
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
                coord = coordinateRepository.findByLatitudeAndLongitude(latitude, longitude);
            }

            s = new Solar(coord, dataPoints, annualAverage);
            solarRepository.save(s);
        }

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
                coord = coordinateRepository.findByLatitudeAndLongitude(latitude, longitude);
            }

            w = new Wind(coord, dataPoints, annualAverage);
            windRepository.save(w);
        }

        windScanner.close();
        solarScanner.close();
    }
}
