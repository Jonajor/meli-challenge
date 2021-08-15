package meli.challenge.quasar.domain.dtos.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class Location {
    private Position position;
    private String message;
}
