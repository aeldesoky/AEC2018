package ca.aec2018.project.model;

import org.springframework.data.repository.CrudRepository;


public interface CoordinateRepository extends CrudRepository<Coordinate, Integer> {

    Coordinate findById(Integer id);

    Coordinate findByLatitudeAndLongitude(Integer latitude, Integer longitude);

}
