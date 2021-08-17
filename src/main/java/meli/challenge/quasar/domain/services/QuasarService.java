package meli.challenge.quasar.domain.services;

import lombok.AllArgsConstructor;
import meli.challenge.quasar.domain.dtos.request.SatelliteData;
import meli.challenge.quasar.domain.dtos.request.SatelliteDto;
import meli.challenge.quasar.domain.dtos.request.TopSecretSplitDto;
import meli.challenge.quasar.domain.dtos.response.LocationDto;
import meli.challenge.quasar.domain.entities.Satellite;
import meli.challenge.quasar.domain.exceptions.SatelliteNotFoundException;
import meli.challenge.quasar.domain.factories.QuasarFactory;
import meli.challenge.quasar.domain.repositories.SatelliteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class QuasarService {

    private final MessageService messageService;
    private final LocationService locationService;
    private final QuasarFactory factory;
    private final SatelliteRepository repository;

    public LocationDto businessProcessor(SatelliteData satelliteData){
        var message = messageService.getMessage(satelliteData);
        var location = locationService.getLocation(satelliteData);
        return factory.buildLocation(location, message);
    }

    public SatelliteDto save(String name, TopSecretSplitDto topSecretSplitDto){
        var latitude = Optional.ofNullable(topSecretSplitDto.getLatitude());
        var longitude = Optional.ofNullable(topSecretSplitDto.getLongitude());
        var satellite = factory.buildEntity(name, topSecretSplitDto, latitude, longitude);
        repository.save(satellite);
        return factory.buildSatelliteDto(satellite);
    }


    public LocationDto getAll() {
        var satellites = repository.findAll();
        var actualList = new ArrayList<SatelliteDto>();
        for (Satellite satellite : satellites){
            actualList.add(factory.buildSatellite(satellite));
        }
        return businessProcessor(SatelliteData.builder().satelliteDtos(actualList).build());
    }

    public SatelliteDto getByName(String name){
        var satellite = repository.findByName(name);

        if (satellite.isPresent()) {
            return factory.buildSatelliteDto(satellite.get());
        } else {
            throw new SatelliteNotFoundException();
        }
    }
}
