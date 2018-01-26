package ca.aec2018.project.model;

import org.springframework.data.repository.CrudRepository;


public interface WindRepository extends CrudRepository<Wind, Integer> {
    Wind findById(Integer id);
    Wind findByCoordinate(Coordinate coordinate);
}
