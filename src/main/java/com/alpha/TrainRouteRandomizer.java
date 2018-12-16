package com.alpha;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class TrainRouteRandomizer {
    static List<TrainRoute>  rendomize(int numberOfRoutes) {
        List<TrainRoute> trainRouteList = new ArrayList<>();
        List<String> strings = new ArrayList<String>(Arrays.<String>asList("Lviv", "Brody", "Rivne", "Dnipro", "Odessa", "Uzhorod", "Ternopil"));
        CarriageType[] carriageTypes = CarriageType.values();
        for (int i = 0; i < numberOfRoutes; i++) {
            TrainRoute trainRoute = new TrainRoute();
            trainRoute.setId(i + 1);
            List<Carriage> carriages = new ArrayList<>();
            int numberOfCarriages = 1 + new Random().nextInt(20);
            for (int j = 0; j < numberOfCarriages; j++) {
                carriages.add(new Carriage(j + 1, carriageTypes[new Random().nextInt(carriageTypes.length)]));
            }
            List<TrainStop> trainStops = new ArrayList<>();
            int numeberOfTrainStops = 2 + new Random().nextInt(strings.size() - 2);
            LocalDateTime localDateTime = LocalDateTime.now();
            localDateTime = localDateTime.minusMinutes(localDateTime.getMinute()).minusSeconds(localDateTime.getSecond());
            for (int j = 0; j < numeberOfTrainStops; j++) {
                trainStops.add(new TrainStop(strings.get(j),localDateTime, localDateTime.plusHours(1)));
                localDateTime.plusHours(2);
            }
            trainRouteList.add(new TrainRoute(i+1,carriages,trainStops));
        }
        return trainRouteList;
    }

    public static void main(String[] args) {
        List<TrainRoute> trainRoutes =rendomize(5);
        System.out.println(trainRoutes);
    }
}
