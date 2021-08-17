package meli.challenge.quasar.controllers;

import meli.challenge.quasar.domain.dtos.request.SatelliteDto;
import meli.challenge.quasar.domain.dtos.request.TopSecretSplitDto;
import meli.challenge.quasar.domain.dtos.response.LocationDto;
import meli.challenge.quasar.domain.dtos.response.PositionDto;
import meli.challenge.quasar.domain.exceptions.InternalServerErrorException;
import meli.challenge.quasar.domain.exceptions.SatelliteNotFoundException;
import meli.challenge.quasar.domain.exceptions.UnprocessableEntityException;
import meli.challenge.quasar.domain.services.QuasarService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.HttpStatus;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
@WebMvcTest
public class SplitControllerTest {

    @InjectMocks
    private SplitController splitController;

    @Mock
    private QuasarService quasarService;

    @Test
    public void saveTopSecretTest(){
        SatelliteDto satelliteDto = new SatelliteDto();
        satelliteDto.setName("teste");
        satelliteDto.setDistance(1.d);
        satelliteDto.setMessage(List.of("Teste", "", "Certo", ""));
        var position = PositionDto.builder().latitude(0.d).longitude(1.d).build();
        satelliteDto.setPositionDto(position);

        TopSecretSplitDto topSecretSplitDto = new TopSecretSplitDto();
        topSecretSplitDto.setDistance(22.d);
        topSecretSplitDto.setMessage(List.of("Teste", "", "Certo", ""));
        topSecretSplitDto.setLongitude(2.d);
        topSecretSplitDto.setLatitude(3.d);

        when(quasarService.save(any(), any())).thenReturn(satelliteDto);
        var result = splitController.saveTopSecret("teste", topSecretSplitDto);
        assertNotNull(result);
        assertEquals(result.getStatusCode(), HttpStatus.CREATED);
        assertEquals(result.getBody().getMessage().size(), satelliteDto.getMessage().size());
    }

    @Test
    public void saveTopSecretTestNOK(){
        when(quasarService.save(any(), any())).thenThrow(UnprocessableEntityException.class);
        assertThrows(UnprocessableEntityException.class, () -> {
            splitController.saveTopSecret(any(), any());
        });
    }

    @Test
    public void getSatelliteNameTest(){
        SatelliteDto satelliteDto = new SatelliteDto();
        satelliteDto.setName("teste");
        satelliteDto.setDistance(1.d);
        satelliteDto.setMessage(List.of("Teste", "", "Certo", ""));
        var position = PositionDto.builder().latitude(0.d).longitude(1.d).build();
        satelliteDto.setPositionDto(position);
        when(quasarService.getByName(any())).thenReturn(satelliteDto);
        var result = splitController.getSatelliteName("teste");
        assertNotNull(result);
        assertEquals(result.getStatusCode(), HttpStatus.OK);
        assertEquals(result.getBody().getName(), "teste");
    }

    @Test
    public void getSatelliteNameTestNOK(){
        when(quasarService.getByName(any())).thenThrow(SatelliteNotFoundException.class);
        assertThrows(SatelliteNotFoundException.class, () -> {
            splitController.getSatelliteName(any());
        });
    }

    @Test
    public void getAllTopSecretTest(){
        var position = PositionDto.builder().latitude(0.d).longitude(1.d).build();
        var location = LocationDto.builder().positionDto(position).message("Teste Certo").build();

        when(quasarService.getAll()).thenReturn(location);
        var result = splitController.getAllTopSecret();
        assertNotNull(result);
        assertEquals(result.getStatusCode(), HttpStatus.OK);
        assertEquals(result.getBody().getMessage(), "Teste Certo");
    }

    @Test
    public void getAllTopSecretTestNOK(){
        when(quasarService.getAll()).thenThrow(InternalServerErrorException.class);
        assertThrows(InternalServerErrorException.class, () -> {
            splitController.getAllTopSecret();
        });
    }
}
