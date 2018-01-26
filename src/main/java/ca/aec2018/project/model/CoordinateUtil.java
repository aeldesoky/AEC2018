package ca.aec2018.project.model;

import ca.aec2018.project.ProjectApplication;

import java.util.ArrayList;

public class CoordinateUtil {


    public Renewable getCoordRenewables(boolean solar, Coordinate coordinate) {

        Renewable renewable;
        int key = 0;
        ArrayList<Coordinate> coordinates = ProjectApplication.coordinates;

        here:
        while(coordinates.iterator().hasNext()){
            if(coordinate.equals(coordinates.get(key))){
                break here;
            }
            key++;
        }

        if(solar){
            renewable = ProjectApplication.solarData.get(key);
        }else {
            renewable = ProjectApplication.windData.get(key);
        }
        return renewable;
    }
}
