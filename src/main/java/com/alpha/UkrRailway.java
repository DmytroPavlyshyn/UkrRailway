package com.alpha;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

public class UkrRailway {
    List<TrainRoute> trainRoutes = new ArrayList<>();
    static ArrayList<TrainStop> trainStops = new ArrayList<TrainStop>() {{
        add(new TrainStop("Lviv", LocalDateTime.of(2018, 12, 6, 8, 30), LocalDateTime.of(2018, 12, 6, 8, 38)));

        add(new TrainStop("Brody", LocalDateTime.now().plusMinutes(40), LocalDateTime.now().plusMinutes(55)));

        add(new TrainStop("Rivne", LocalDateTime.now().plusMinutes(67), LocalDateTime.now().plusMinutes(89)));

        add(new TrainStop("Kyiv", LocalDateTime.now().plusMinutes(100), LocalDateTime.now().plusMinutes(115)));
    }};

    {
        trainRoutes.add(new TrainRoute(
                new Train(new ArrayList<Carriage>() {{
                    add(new Carriage(1));
                }}), trainStops));
    }

    List<TrainRoute> FindAllAvailabes(String from, String to, LocalDateTime dapatureTime) {
        List<TrainRoute> AvailableTrainRoutes = new ArrayList<>();
        for (TrainRoute trainRoute : trainRoutes) {
            if (!dapatureTime.equals(trainRoute.getDepatureDate())) {
                continue;
            }
            int fromIndex = trainRoute.findIndexOfTrainStopByName(from);
            int toIndex = trainRoute.findIndexOfTrainStopByName(to);

            if (fromIndex == -1 || toIndex == -1) {
                continue;
            }
            if (fromIndex >= toIndex) {
                continue;
            }
            int generalCapacity = trainRoute.getTrain().getGeneralCapacity();
            int oneStopCapacity = 0;
            boolean isFull = false;
            for (TrainStop trainStop : trainRoute.getTrainStops()) {

                for (Ticket2 ticket : trainRoute.getTickets()) {
                    if (ticket.getPersonalRoute().contains(trainStop)) {
                        oneStopCapacity++;
                    }
                }
                if (oneStopCapacity == generalCapacity) {
                    isFull = true;
                }
            }
            if (!isFull) {
                AvailableTrainRoutes.add(trainRoute);
            }
        }
        return AvailableTrainRoutes;
    }

    public static void main(String[] args) {
        UkrRailway ukrRailway = new UkrRailway();
        boolean isEmpty = ukrRailway.FindAllAvailabes("Lviv", "Rivne", LocalDateTime.of(2018, 12, 6, 8, 38))
                .isEmpty();
        System.out.println(isEmpty);
        ukrRailway.trainRoutes.get(0).getTickets().add(new Ticket2(
                LocalDateTime.of(2018, 12, 6, 8, 38),
                "Jack",
                "Daniels",
                1,
                1,
                1,
                new ArrayList<TrainStop>() {{
                    System.out.println(trainStops.subList(0,3));
                    addAll(trainStops.subList(0,4));
                }}
        ));
       isEmpty = ukrRailway.FindAllAvailabes("Lviv", "Rivne", LocalDateTime.of(2018, 12, 6, 8, 38))
               .isEmpty();
        System.out.println(isEmpty);
    }
}
