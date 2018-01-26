package ca.aec2018.project.model;

import org.springframework.data.repository.CrudRepository;


public interface CoordinateRepository extends CrudRepository<Coordinate, Integer> {
    Coordinate findByLatitudeAndLongitude(Integer latitude, Integer longitude);

}
