package meli.challenge.quasar.domain.services;

import meli.challenge.quasar.domain.dtos.request.SatelliteData;
import meli.challenge.quasar.domain.dtos.response.Location;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QuasarService {

    @Autowired
    MessageService messageService;

    @Autowired
    LocationService locationService;

    public Object businessProcessor(SatelliteData satelliteData){
        var message = messageService.getMessage(satelliteData);
        var location = locationService.getLocation(satelliteData);
        return Location.builder()
                .position(location)
                .message(message)
                .build();
    }


}
