package meli.challenge.quasar.domain.dtos.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import meli.challenge.quasar.domain.dtos.response.PositionDto;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class NaveDto {
    @JsonProperty(value = "position")
    private PositionDto positionDto;
}
