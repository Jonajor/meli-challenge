package meli.challenge.quasar.domain.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name="Satellites")
public class Satellite {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    @Column(name = "SATELLITE_NAME", nullable = false)
    private String name;

    @Column(name = "DISTANCE", nullable = false)
    private Double distance;

    @ElementCollection
    @Column(name = "MESSAGE", nullable = false)
    private List<String> message;

    @Column(name = "LATITUDE")
    private Double latitude;

    @Column(name = "LONGITUDE")
    private Double longitude;
}
