package com.alpha;

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
    static ArrayList<TrainStop> trainStops2 = new ArrayList<TrainStop>() {{

        add(new TrainStop("Lviv", LocalDateTime.of(2018, 12, 6, 8, 30), LocalDateTime.of(2018, 12, 6, 8, 38)));

        add(new TrainStop("Brody", LocalDateTime.now().plusMinutes(40), LocalDateTime.now().plusMinutes(55)));

        add(new TrainStop("Hogwarts", LocalDateTime.now().plusMinutes(50), LocalDateTime.now().plusMinutes(66)));

        add(new TrainStop("Rivne", LocalDateTime.now().plusMinutes(67), LocalDateTime.now().plusMinutes(89)));

        add(new TrainStop("Kyiv", LocalDateTime.now().plusMinutes(100), LocalDateTime.now().plusMinutes(115)));
    }};
    {
        trainRoutes.add(new TrainRoute(
                new Train(new ArrayList<Carriage>() {{
                    add(new Carriage(1));
                }}), trainStops));
        trainRoutes.add(new TrainRoute(
                new Train(new ArrayList<Carriage>() {{
                    add(new Carriage(1));
                }}), trainStops2));

    }

    List<TrainRoute> findAllAvailabes(String from, String to, LocalDateTime dapatureTime) {
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
    TrainRoute findShortestRoute(String from, String to, LocalDateTime localDateTime){
        List<TrainRoute> availableRoutes = findAllAvailabes(from,to,localDateTime);
        TrainRoute shortestRoute = null;
        int minimalDistance = -1;
        if(availableRoutes.isEmpty()){
            return null;
        }

        for(TrainRoute trainRoute:availableRoutes){
            if(minimalDistance < 0){
                minimalDistance = trainRoute.findIndexOfTrainStopByName(to) - trainRoute.findIndexOfTrainStopByName(from);
                shortestRoute = trainRoute;
            }
            else if(trainRoute.findIndexOfTrainStopByName(to) - trainRoute.findIndexOfTrainStopByName(from)<minimalDistance){
                minimalDistance = trainRoute.findIndexOfTrainStopByName(to) - trainRoute.findIndexOfTrainStopByName(from);
                shortestRoute = trainRoute;
            }

        }
        return shortestRoute;
    }
    void buyTicket(String from, String to,LocalDateTime localDateTime, String firstName, String lastName){
        TrainRoute shortestRoute = findShortestRoute("Lviv", "Rivne", LocalDateTime.of(2018, 12, 6, 8, 38));
        if(shortestRoute == null) {
            System.out.println("There's no such route");
            return;
        }
        shortestRoute.getTickets().add(new Ticket2(
                localDateTime,
                firstName,
                lastName,
                shortestRoute.getTrain().getId(),
                //доробити вибір вибір вагону та місця та генерування особистого маршруту
                1,
                1,
                new ArrayList<TrainStop>() {{
                    System.out.println(trainStops.subList(0,3));
                    addAll(trainStops.subList(0,4));
                }}
        ));
        System.out.println("You successfully bought a ticket");
    }
    public static void main(String[] args) {
        UkrRailway ukrRailway = new UkrRailway();
        boolean isEmpty = ukrRailway.findAllAvailabes("Lviv", "Rivne", LocalDateTime.of(2018, 12, 6, 8, 38))
                .isEmpty();
        System.out.println(isEmpty);
        System.out.println(" The fastest way" + ukrRailway.findShortestRoute("Lviv", "Rivne", LocalDateTime.of(2018, 12, 6, 8, 38)));
        isEmpty = ukrRailway.findAllAvailabes("Lviv", "Rivne", LocalDateTime.of(2018, 12, 6, 8, 38)).isEmpty();
        System.out.println(ukrRailway.trainRoutes);
        System.out.println(isEmpty);
    }
}
