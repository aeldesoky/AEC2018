package ca.aec2018.project.model;

import org.springframework.data.repository.CrudRepository;


public interface SolarRepository extends CrudRepository<Solar, Integer> {
    Solar findById(Integer id);
    Solar findByCoordinate(Coordinate coordinate);
}
