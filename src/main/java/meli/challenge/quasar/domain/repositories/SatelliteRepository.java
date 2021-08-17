package meli.challenge.quasar.domain.repositories;

import meli.challenge.quasar.domain.entities.Satellite;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface SatelliteRepository extends CrudRepository<Satellite, Long> {

    Optional<Satellite> findByName(String name);

}
