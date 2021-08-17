package meli.challenge.quasar.controllers;

import lombok.AllArgsConstructor;
import meli.challenge.quasar.domain.dtos.request.SatelliteData;
import meli.challenge.quasar.domain.dtos.response.LocationDto;
import meli.challenge.quasar.domain.exceptions.UnprocessableEntityException;
import meli.challenge.quasar.domain.services.QuasarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor(onConstructor = @__(@Autowired))
@RestController
@RequestMapping("/topsecret")
public class QuasarController {

    private final QuasarService quasarService;

    @PostMapping
    public ResponseEntity<LocationDto> saveSecret(@Validated @RequestBody SatelliteData satelliteData){
        try {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(quasarService.businessProcessor(satelliteData));
        } catch (RuntimeException e){
            throw new UnprocessableEntityException();
        }
    }
}
