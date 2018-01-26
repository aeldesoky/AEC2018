package ca.aec2018.project.model;

import com.sun.deploy.resources.Deployment_pt_BR;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class RenewableService {
    @Autowired
    Renewable renewable;

    @Autowired
    SolarRepository solarRepository;

    @Autowired
    WindRepository windRepository;


    /**
     * This method is used to convert the renewable object into a googleRenewable object.
     * The googleRenewable object is used with the Google Maps API for the heatMap layer.
     * The googleRenewable object is based on the GoogleRenewable class, which has the same
     * parameters used by the google maps API to generate the heatMap Layer.
     * @param solar  - boolean to check if the data is for solar energy
     * @param id     - int used to get the solar or wind repository.
     * @param month  - int used to identify the month
     */
    public void renewableToGoogleRenewable(boolean solar, int id, int month) {
        Renewable renewable;
        Double[] coordinates = new Double[2];
        GoogleRenewable googleRenewable = new GoogleRenewable();
        Geometry googleGeometry = new Geometry();
        Properties googleProperties = new Properties();
        Coordinate coordinateObj;

        if (solar) {
            renewable = solarRepository.findOne(id);
        }

        else {
            renewable = windRepository.findOne(id);
        }


        googleGeometry.setType("Point");
        coordinateObj = renewable.getCoordinate();
        coordinates[0] = coordinateObj.getLatitude().doubleValue();
        coordinates[1] = coordinateObj.getLongitude().doubleValue();

        googleGeometry.setCoordinates(coordinates);
        googleRenewable.setGeometry(googleGeometry);

        switch (month) {
            case 1:
        }
        googleProperties.setMag(monthsMag[month]);
        googleRenewable.setProperties(googleProperties);

        googleRenewable.setType("Feature");


    }

    public List<GoogleRenewable> allRenewableToGoogleRenewable(boolean solar, int month) {
        return null;
    }
}
