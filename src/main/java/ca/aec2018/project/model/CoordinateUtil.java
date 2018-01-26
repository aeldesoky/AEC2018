package ca.aec2018.project.model;

import ca.aec2018.project.ProjectApplication;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.lang.Math;

@Service
public class CoordinateUtil {

    public Renewable getCoordRenewables(boolean solar, double longitude, double latitude){
        Coordinate coordinate = new Coordinate(longitude, latitude);
        return getCoordRenewables(solar, coordinate);
    }

    public ArrayList<Renewable> renewablesInRange(Coordinate c1, Coordinate c2){
        return null;
    }

    public Renewable getCoordRenewables(boolean solar, Coordinate coordinate) {

        Renewable renewable;
        int key = 0;
        ArrayList<Coordinate> coordinates = ProjectApplication.coordinates;

        here:
        while(coordinates.iterator().hasNext()){
            if(coordinate.sameCoord(coordinates.get(key))){
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
