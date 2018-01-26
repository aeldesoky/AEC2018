package ca.aec2018.project.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Solar implements Renewable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @OneToOne
    private Coordinate coordinate;

    @ElementCollection
    private List<Double> months;

    private Double annualAverage;

    public Solar(Coordinate coordinate, List<Double> months, Double annualAverage) {
        this.coordinate = coordinate;
        this.months = months;
        this.annualAverage = annualAverage;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public List<Double> getMonths() {
        return months;
    }

    public void setMonths(List<Double> months) {
        this.months = months;
    }

    @Override
    public Double getAnnualAverage() {
        return annualAverage;
    }

    public void setAnnualAverage(Double annualAverage) {
        this.annualAverage = annualAverage;
    }

    @Override
    public Coordinate getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(Coordinate coordinate) {
        this.coordinate = coordinate;
    }
}
