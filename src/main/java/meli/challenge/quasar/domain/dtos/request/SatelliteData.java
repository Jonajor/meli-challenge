package meli.challenge.quasar.domain.dtos.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SatelliteData {
    @JsonProperty(value = "satellites")
    private List<SatelliteDto> satelliteDtos;
}
