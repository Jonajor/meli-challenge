package meli.challenge.quasar.domain.dtos.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TopSecretSplitDto {
    @NotNull(message = "Distance cannot be null")
    private Double distance;
    @NotNull(message = "Message cannot be null")
    private List<String> message;
    private Double latitude;
    private Double longitude;
}
