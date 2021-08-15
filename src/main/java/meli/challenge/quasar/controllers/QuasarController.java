package meli.challenge.quasar.controllers;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import meli.challenge.quasar.domain.dtos.request.SatelliteData;
import meli.challenge.quasar.domain.services.QuasarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@NoArgsConstructor
@RestController
@RequestMapping("/topsecret")
public class QuasarController {

    @Autowired
    QuasarService quasarService;

    @PostMapping
    private ResponseEntity<Object> saveSecret(@Validated @RequestBody SatelliteData satelliteData){
        return ResponseEntity.status(HttpStatus.OK)
                .body(quasarService.businessProcessor(satelliteData));
    }
}
