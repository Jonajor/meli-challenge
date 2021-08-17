package meli.challenge.quasar.controllers;

import meli.challenge.quasar.domain.dtos.request.SatelliteData;
import meli.challenge.quasar.domain.dtos.request.SatelliteDto;
import meli.challenge.quasar.domain.dtos.response.LocationDto;
import meli.challenge.quasar.domain.dtos.response.PositionDto;
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
public class QuasarControllerTest {

    @InjectMocks
    private QuasarController quasarController;

    @Mock
    private QuasarService quasarService;

    @Test
    public void saveSecretTest(){
        SatelliteDto satelliteDto = new SatelliteDto();
        satelliteDto.setName("teste");
        satelliteDto.setDistance(1.d);
        satelliteDto.setMessage(List.of("Teste", "", "Certo", ""));
        var position = PositionDto.builder().latitude(0.d).longitude(1.d).build();
        satelliteDto.setPositionDto(position);
        var location = LocationDto.builder().positionDto(position).message("Teste Certo").build();

        var satellieteDate = SatelliteData.builder().satelliteDtos(List.of(satelliteDto)).build();

        when(quasarService.businessProcessor(satellieteDate)).thenReturn(location);
        var result = quasarController.saveSecret(satellieteDate);
        assertNotNull(result);
        assertEquals(result.getStatusCode(), HttpStatus.OK);
        assertEquals(result.getBody().getMessage(), "Teste Certo");
    }

    @Test
    public void saveSecretTestNOK(){
        when(quasarService.businessProcessor(any())).thenThrow(UnprocessableEntityException.class);
        assertThrows(UnprocessableEntityException.class, () -> {
            quasarController.saveSecret(any());
        });
    }
}
