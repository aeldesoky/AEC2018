package ca.aec2018.project

import javal.util.Scanner


@SpringBootApplication
public class DataProcessingApplication {

    @Autowired
    WindRepository windRepository;

    @Autowired
    SolarRepository solarRepository;

    @Autowired
    CoordinateRepository coordinateRepository;

    public static void main(String[] args) {
        SpringApplication.run(DataProcessingApplication.class, args);

        File solarFile = new File("resources/solarNASA.txt");
        File windFile = new File("resources/windNASA.txt");

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
