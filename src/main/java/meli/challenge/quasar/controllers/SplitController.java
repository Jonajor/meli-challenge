package meli.challenge.quasar.controllers;

import lombok.RequiredArgsConstructor;
import meli.challenge.quasar.domain.dtos.request.SatelliteDto;
import meli.challenge.quasar.domain.dtos.request.TopSecretSplitDto;
import meli.challenge.quasar.domain.dtos.response.LocationDto;
import meli.challenge.quasar.domain.exceptions.InternalServerErrorException;
import meli.challenge.quasar.domain.exceptions.SatelliteNotFoundException;
import meli.challenge.quasar.domain.exceptions.UnprocessableEntityException;
import meli.challenge.quasar.domain.services.QuasarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@RestController
@RequestMapping("/topsecret_split")
public class SplitController {

    private final QuasarService quasarService;

    @PostMapping("/{satellite_name}")
    public ResponseEntity<SatelliteDto> saveTopSecret(@PathVariable("satellite_name") String name,
                                                      @Validated @RequestBody TopSecretSplitDto topSecretSplitDto){
        try{
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(quasarService.save(name, topSecretSplitDto));
        } catch (RuntimeException e){
            throw new UnprocessableEntityException();
        }
    }

    @GetMapping("/{satellite_name}")
    public ResponseEntity<SatelliteDto> getSatelliteName(@PathVariable("satellite_name") String name){

        try{
            return ResponseEntity.status(HttpStatus.OK)
                    .body(quasarService.getByName(name));
        } catch (RuntimeException e){
            throw new SatelliteNotFoundException();
        }
    }

    @GetMapping
    public ResponseEntity<LocationDto> getAllTopSecret(){
        try{
            return ResponseEntity.status(HttpStatus.OK)
                    .body(quasarService.getAll());
        } catch (RuntimeException e){
            throw new InternalServerErrorException();
        }
    }
}
