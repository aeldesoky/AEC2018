package ca.aec2018.project

import javal.util.Scanner


@SpringBootApplication
public class DataProcessingApplication {

    @Autowired
    WindRepository windRepository;

    @Autowired
    SolarRepository solarRepository;

    public static void main(String[] args) {


        SpringApplication.run(DataProcessingApplication.class, args);

        File solarFile = new File("resources/solarNASA.txt");
        File windFile = new File("resources/windNASA.txt");

        Scanner sc = new Scanner(solarFile);

        String[] line = sc.nextLine().split(" ");

        Integer latitude = Integer.parseInt(line[0]);
        Integer longitude = Integer.parseInt(line[1]);

        Double[] dataPoints = new Double[12];

        for(int index = 0; index < 12; index++) {
            dataPoints[index] = Double.parseDouble(line[2 + index]);
        }

        Double annualAverage = Double.parseDouble(line[13]);

        Coordinate coord = new Coordinate()

        sc = new Scanner(solarFile);


    }
}
