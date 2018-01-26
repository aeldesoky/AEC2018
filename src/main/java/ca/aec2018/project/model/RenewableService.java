package ca.aec2018.project.model;

import com.sun.deploy.resources.Deployment_pt_BR;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

public class RenewableService {
    @Autowired
    Renewable renewable;

    @Autowired
    SolarRepository solarRepository;

    @Autowired
    WindRepository windRepository;


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

        Double[] monthsMag = renewable.getMonths();
        googleProperties.setMag(monthsMag[month]);
        googleRenewable.setProperties(googleProperties);

        googleRenewable.setType("Feature");


    }
}
