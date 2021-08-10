package meli.challenge.quasar.controller;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import meli.challenge.quasar.domain.dtos.request.SatelliteData;
import meli.challenge.quasar.domain.dtos.response.Location;
import meli.challenge.quasar.domain.services.QuasarService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@NoArgsConstructor
@RestController
@RequestMapping("/topsecret")
public class QuasarController {

    private QuasarService quasarService;

    @PostMapping
    private ResponseEntity<Location> saveSecret(SatelliteData satelliteData){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(quasarService.getLocation(satelliteData));
    }
}
