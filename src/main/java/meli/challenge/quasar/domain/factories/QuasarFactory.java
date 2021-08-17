package meli.challenge.quasar.domain.factories;

import meli.challenge.quasar.domain.dtos.request.SatelliteDto;
import meli.challenge.quasar.domain.dtos.request.TopSecretSplitDto;
import meli.challenge.quasar.domain.dtos.response.LocationDto;
import meli.challenge.quasar.domain.dtos.response.PositionDto;
import meli.challenge.quasar.domain.entities.Satellite;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class QuasarFactory {

    public LocationDto buildLocation(PositionDto location, String message){
        return LocationDto.builder()
                .positionDto(location)
                .message(message)
                .build();
    }

    public PositionDto buildPosition(Double latitude, Double longitude){
        return PositionDto.builder()
                .latitude(latitude)
                .longitude(longitude)
                .build();
    }

    public SatelliteDto buildSatellite(Satellite satellite){
        SatelliteDto satelliteDto = new SatelliteDto();
        var position = buildPosition(satellite.getLatitude(), satellite.getLongitude());
        satelliteDto.setName(satellite.getName());
        satelliteDto.setDistance(satellite.getDistance());
        satelliteDto.setMessage(satellite.getMessage());
        satelliteDto.setPositionDto(position);
        return satelliteDto;
    }

    public Satellite buildEntity(String name, TopSecretSplitDto topSecretSplitDto, Optional<Double> latitude, Optional<Double> longitude){
        return Satellite.builder()
                .name(name)
                .distance(topSecretSplitDto.getDistance())
                .message(topSecretSplitDto.getMessage())
                .latitude(latitude.orElse(0.d))
                .longitude(longitude.orElse(0.d))
                .build();
    }

    public SatelliteDto buildSatelliteDto(Satellite satelliteEntity) {
        SatelliteDto satelliteDto = new SatelliteDto();
        satelliteDto.setName(satelliteEntity.getName());
        satelliteDto.setDistance(satelliteEntity.getDistance());
        satelliteDto.setMessage(satelliteEntity.getMessage());
        satelliteDto.setPositionDto(buildPosition(satelliteEntity.getLatitude(), satelliteEntity.getLongitude()));
        return satelliteDto;
    }
}
