package meli.challenge.quasar.domain.services;

import meli.challenge.quasar.domain.dtos.request.SatelliteData;
import meli.challenge.quasar.domain.dtos.response.Location;
import org.springframework.stereotype.Service;

@Service
public class QuasarService {

    public Location getLocation(SatelliteData satelliteData){
        satelliteData.getSatellites().stream().findAny().get().getMessage();
        return null;
    }
}
