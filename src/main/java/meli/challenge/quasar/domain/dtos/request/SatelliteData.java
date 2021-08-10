package meli.challenge.quasar.domain.dtos.request;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class SatelliteData {
    private List<Satellite> satellites;
}
