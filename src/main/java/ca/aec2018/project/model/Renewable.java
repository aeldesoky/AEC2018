package ca.aec2018.project.model;

import java.util.List;

/**
 * This interface us used for the Wind and Solar Classes. It gives user access to
 * the coordinates, the annual average, and the data associated with each month.
 */
public interface Renewable {
    Double getAnnualAverage();
    Coordinate getCoordinate();
    Double getJanuary();
    Double getFebruary();
    Double getMarch();
    Double getApril();
    Double getMay();
    Double getJune();
    Double getJuly();
    Double getAugust();
    Double getSeptember();
    Double getOctober();
    Double getNovember();
    Double getDecember();
}
