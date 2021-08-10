package meli.challenge.quasar.domain.dtos.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Location {
    private Position position;
    private String message;
}
