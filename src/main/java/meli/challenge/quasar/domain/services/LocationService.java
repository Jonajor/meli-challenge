package meli.challenge.quasar.domain.services;

import com.lemmingapex.trilateration.NonLinearLeastSquaresSolver;
import com.lemmingapex.trilateration.TrilaterationFunction;
import meli.challenge.quasar.domain.dtos.request.SatelliteData;
import meli.challenge.quasar.domain.dtos.response.Position;
import org.apache.commons.math3.fitting.leastsquares.LeastSquaresOptimizer.Optimum;
import org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer;
import org.springframework.stereotype.Service;

import java.util.stream.IntStream;

@Service
public class LocationService {

    public Position getLocation(SatelliteData satelliteData) {
        var satellitePosition = new double[satelliteData.getSatellites().size()][2];
        var distance = new double[satelliteData.getSatellites().size()];

        IntStream.range(0, satelliteData.getSatellites().size()).forEach(i -> {
            positionIsPresent(satelliteData, satellitePosition, i);
            distance[i] = satelliteData.getSatellites().get(i).getDistance();
        });

        var solver = new NonLinearLeastSquaresSolver(
                new TrilaterationFunction(satellitePosition, distance), new LevenbergMarquardtOptimizer());
        Optimum optimum = solver.solve();
        var x = optimum.getPoint().getEntry(0);
        var y = optimum.getPoint().getEntry(1);
        return Position.builder()
                .latitude(x)
                .longitude(y)
                .build();
    }

    private void positionIsPresent(SatelliteData satelliteData, double[][] satellitePosition, int i) {
        satellitePosition[i][0] = 0.d;
        satellitePosition[i][1] = 0.d;
        if (satelliteData.getSatellites().get(i).getPosition() != null){
            satellitePosition[i][0] = satelliteData.getSatellites().get(i).getPosition().getLatitude();
            satellitePosition[i][1] = satelliteData.getSatellites().get(i).getPosition().getLongitude();
        }

    }

}
