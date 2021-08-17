package meli.challenge.quasar.domain.dtos.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SatelliteDto extends NaveDto {
    private String name;
    private Double distance;
    private List<String> message;
}
