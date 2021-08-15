package meli.challenge.quasar.domain.dtos.request;

import lombok.Getter;
import lombok.Setter;
import meli.challenge.quasar.domain.services.Nave;

import java.util.List;

@Getter
@Setter
public class Satellite extends Nave {
    private String name;
    private Double distance;
    private List<String> message;
}
