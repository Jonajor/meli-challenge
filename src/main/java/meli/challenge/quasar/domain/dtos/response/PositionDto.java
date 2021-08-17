package meli.challenge.quasar.domain.dtos.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PositionDto {
    @JsonProperty(value = "x")
    private Double latitude;
    @JsonProperty(value = "y")
    private Double longitude;
}
