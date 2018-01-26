package ca.aec2018.project.model;

import javax.persistence.*;

@Entity
public class Wind {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @OneToOne
    private Coordinate coordinate;

    private Double[] months;

    private Double annualAverage;

    public Wind(Coordinate coordinate, Double[] months, Double annualAverage) {
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

    public Double[] getMonths() {
        return months;
    }

    public void setMonths(Double[] months) {
        this.months = months;
    }

    public Double getAnnualAverage() {
        return annualAverage;
    }

    public void setAnnualAverage(Double annualAverage) {
        this.annualAverage = annualAverage;
    }
}
