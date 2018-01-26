package ca.aec2018.project.model;

import java.util.List;

public interface Renewable {
    List<Double> getMonths();
    Double getAnnualAverage();
    Coordinate getCoordinate();
}
