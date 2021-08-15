package meli.challenge.quasar.domain.dtos.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class Position {
    @JsonProperty(value = "x")
    private Double latitude;
    @JsonProperty(value = "y")
    private Double longitude;
}
