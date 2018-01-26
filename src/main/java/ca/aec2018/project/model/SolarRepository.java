package ca.aec2018.project.model;

import org.springframework.data.repository.CrudRepository;


public interface SolarRepository extends CrudRepository<Solar, Integer> {
    Solar findByCoordinate(Coordinate coordinate);
}
