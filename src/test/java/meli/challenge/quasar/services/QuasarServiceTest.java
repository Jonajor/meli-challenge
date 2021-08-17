package meli.challenge.quasar.services;

import meli.challenge.quasar.domain.dtos.request.SatelliteData;
import meli.challenge.quasar.domain.dtos.request.SatelliteDto;
import meli.challenge.quasar.domain.dtos.request.TopSecretSplitDto;
import meli.challenge.quasar.domain.dtos.response.LocationDto;
import meli.challenge.quasar.domain.dtos.response.PositionDto;
import meli.challenge.quasar.domain.entities.Satellite;
import meli.challenge.quasar.domain.exceptions.SatelliteNotFoundException;
import meli.challenge.quasar.domain.factories.QuasarFactory;
import meli.challenge.quasar.domain.repositories.SatelliteRepository;
import meli.challenge.quasar.domain.services.LocationService;
import meli.challenge.quasar.domain.services.MessageService;
import meli.challenge.quasar.domain.services.QuasarService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class QuasarServiceTest {
    @Mock
    private MessageService messageService;
    @Mock
    private LocationService locationService;
    @Mock
    private QuasarFactory factory;
    @Mock
    private SatelliteRepository repository;

    @InjectMocks
    private QuasarService quasarService;

    SatelliteData satellieteDate = new SatelliteData();
    PositionDto positionDto = PositionDto.builder().build();
    LocationDto locationDto = LocationDto.builder().build();
    TopSecretSplitDto topSecretSplitDto = new TopSecretSplitDto();
    Satellite satellite = Satellite.builder().build();
    SatelliteDto satelliteDto = new SatelliteDto();

    @Before
    public void before(){

        satelliteDto.setName("teste");
        satelliteDto.setDistance(1.d);
        satelliteDto.setMessage(List.of("Teste", "", "Certo", ""));
        positionDto.setLatitude(0.d);
        positionDto.setLongitude(1.d);
        satelliteDto.setPositionDto(positionDto);
        locationDto.setPositionDto(positionDto);
        locationDto.setMessage("Teste Certo");

        satellieteDate.setSatelliteDtos(List.of(satelliteDto));
        topSecretSplitDto.setDistance(22.d);
        topSecretSplitDto.setMessage(List.of("Teste", "", "Certo", ""));
        topSecretSplitDto.setLongitude(2.d);
        topSecretSplitDto.setLatitude(3.d);

        satellite.setName("teste");
        satellite.setDistance(topSecretSplitDto.getDistance());
        satellite.setMessage(topSecretSplitDto.getMessage());
        satellite.setLatitude(topSecretSplitDto.getLatitude());
        satellite.setLongitude(topSecretSplitDto.getLongitude());

    }

    @Test
    public void businessProcessorTest(){
        when(messageService.getMessage(satellieteDate)).thenReturn("Teste Certo");
        when(locationService.getLocation(satellieteDate)).thenReturn(positionDto);
        when(factory.buildLocation(any(), any())).thenReturn(locationDto);
        var result = quasarService.businessProcessor(satellieteDate);
        assertNotNull(result);
        assertEquals(result.getMessage(), "Teste Certo");
    }

    @Test
    public void saveTest(){
        when(factory.buildEntity(any(), any(), any(), any())).thenReturn(satellite);
        when(repository.save(any())).thenReturn(satellite);
        when(factory.buildSatelliteDto(any())).thenReturn(satelliteDto);
        var result = quasarService.save("teste", topSecretSplitDto);
        assertNotNull(result);
    }

    @Test
    public void getAllTest(){
        when(repository.findAll()).thenReturn(List.of(satellite));
        when(factory.buildSatellite(any())).thenReturn(satelliteDto);
        when(quasarService.businessProcessor(any())).thenReturn(locationDto);
        var result = quasarService.getAll();
        assertNotNull(result);
    }

    @Test
    public void getByNameTest(){
        when(repository.findByName(any())).thenReturn(Optional.of(satellite));
        when(factory.buildSatelliteDto(any())).thenReturn(satelliteDto);
        var result = quasarService.getByName(satellite.getName());
        assertNotNull(result);
    }

    @Test
    public void getByNameTestNOK(){
        when(repository.findByName(any())).thenReturn(Optional.empty());
        assertThrows(SatelliteNotFoundException.class, () -> {
            quasarService.getByName(any());
        });
    }
}
