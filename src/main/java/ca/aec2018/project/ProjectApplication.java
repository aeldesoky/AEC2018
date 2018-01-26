package ca.aec2018.project;

import ca.aec2018.project.model.Coordinate;
import ca.aec2018.project.model.Solar;
import ca.aec2018.project.model.Wind;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

@SpringBootApplication
public class ProjectApplication {

	public static ArrayList<Coordinate> coordinates = new ArrayList<Coordinate>();

	public static HashMap<Integer, Wind> windData = new HashMap<Integer, Wind>();

	public static HashMap<Integer, Solar> solarData = new HashMap<Integer, Solar>();

	public static void main(String[] args) {

		// load data
		File solarFile = new File("src/main/resources/solarNASA.txt");
		File windFile = new File("src/main/resources/windNASA.txt");

		try {
			Scanner solarScanner = new Scanner(solarFile);
			Scanner windScanner = new Scanner(windFile);

		String[] line;
		Coordinate coord;
		Solar s;
		Wind w;
		Integer latitude;
		Integer longitude;
		Double annualAverage;
		int id = 0;

		System.out.println("===========Parsing Solar and Wind Data===========");
		while(solarScanner.hasNextLine() && windScanner.hasNextLine()) {

			//Parse Solar Data
			line = solarScanner.nextLine().split(" ");
			latitude = Integer.parseInt(line[0]);
			longitude = Integer.parseInt(line[1]);

			annualAverage = Double.parseDouble(line[13]);

			coord = new Coordinate(latitude, longitude);

			coordinates.add(coord);

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

			solarData.put(id ,s);

			line = windScanner.nextLine().split(" ");

			annualAverage = Double.parseDouble(line[13]);

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

			windData.put(id , w);

			if(id%100 == 0){
				System.out.println("Saved Objects: " + id);
			}
			id++;
		}

		windScanner.close();
		solarScanner.close();

		}catch(Exception e){
			System.out.println("File Not Found");
		}

		SpringApplication.run(ProjectApplication.class, args);
	}
}
