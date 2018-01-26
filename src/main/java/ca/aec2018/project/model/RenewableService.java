package ca.aec2018.project.model;

import ca.aec2018.project.ProjectApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

@Service
public class RenewableService {

    /**
     * This method is used to convert the renewable object into a googleRenewable object.
     * The googleRenewable object is used with the Google Maps API for the heatMap layer.
     * The googleRenewable object is based on the GoogleRenewable class, which has the same
     * parameters used by the google maps API to generate the heatMap Layer.
     * @param solar  - boolean to check if the data is for solar energy
     *     - int used to get the solar or wind repository.
     * @param month  - int used to identify the month
     */
    public List<GoogleRenewable> getRenewablesByMonth(boolean solar, int month) {
        ArrayList<GoogleRenewable> googleRenewables = new ArrayList<GoogleRenewable>();


        if (solar) {

            HashMap<Integer, Solar> solars = ProjectApplication.solarData;
            Set<Integer> keys = solars.keySet();
            for(Integer key : keys) {
                googleRenewables.add(renewableToGoogleRenewable(solars.get(key),month));
            }
        }

        else {

            HashMap<Integer, Wind> winds = ProjectApplication.windData;
            Set<Integer> keys = winds.keySet();
            for(Integer key : keys) {
                googleRenewables.add(renewableToGoogleRenewable(winds.get(key),month));
            }
        }

        return googleRenewables;
    }

    private GoogleRenewable renewableToGoogleRenewable(Renewable renewable, int month){
        Double[] coordinates = new Double[2];
        GoogleRenewable googleRenewable = new GoogleRenewable();
        Geometry googleGeometry = new Geometry();
        Properties googleProperties = new Properties();
        Coordinate coordinateObj;

        googleGeometry.setType("Point");
        coordinateObj = renewable.getCoordinate();
        coordinates[0] = coordinateObj.getLatitude().doubleValue();
        coordinates[1] = coordinateObj.getLongitude().doubleValue();

        googleGeometry.setCoordinates(coordinates);
        googleRenewable.setGeometry(googleGeometry);

        switch (month) {
            case 0:
                googleProperties.setMag(renewable.getJanuary());
                break;
            case 1:
                googleProperties.setMag(renewable.getFebruary());
                break;
            case 2:
                googleProperties.setMag(renewable.getMarch());
                break;
            case 3:
                googleProperties.setMag(renewable.getApril());
                break;
            case 4:
                googleProperties.setMag(renewable.getMay());
                break;
            case 5:
                googleProperties.setMag(renewable.getJune());
                break;
            case 6:
                googleProperties.setMag(renewable.getJuly());
                break;
            case 7:
                googleProperties.setMag(renewable.getAugust());
                break;
            case 8:
                googleProperties.setMag(renewable.getSeptember());
                break;
            case 9:
                googleProperties.setMag(renewable.getOctober());
                break;
            case 10:
                googleProperties.setMag(renewable.getNovember());
                break;
            case 11:
                googleProperties.setMag(renewable.getDecember());
                break;

        }
        googleRenewable.setProperties(googleProperties);

        googleRenewable.setType("Feature");

        return googleRenewable;

    }
}
