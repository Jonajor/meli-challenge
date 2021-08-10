package meli.challenge.quasar.domain.dtos.request;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Satellite {
    private String name;
    private Double distance;
    private List<String> message;
}
