package ca.aec2018.project.model;

import ca.aec2018.project.ProjectApplication;

import java.util.ArrayList;
import java.lang.Math;

public class CoordinateUtil {

    public Renewable getCoordRenewables(boolean solar, double longitude, double latitude){
        Coordinate coordinate = new Coordinate(longitude, latitude);
        return getCoordRenewables(solar, coordinate);
    }

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
