package com.alpha;

import java.time.LocalDateTime;
import java.util.*;

public class UkrRailway {
    List<TrainRoute> trainRoutes = new ArrayList<>();
    static Scanner scanner = new Scanner(System.in);
    static ArrayList<TrainStop> trainStops = new ArrayList<TrainStop>() {{

        add(new TrainStop("Lviv", LocalDateTime.of(2018, 12, 6, 8, 30), LocalDateTime.of(2018, 12, 6, 8, 38)));

        add(new TrainStop("Brody", LocalDateTime.now().plusMinutes(40), LocalDateTime.now().plusMinutes(55)));

        add(new TrainStop("Rivne", LocalDateTime.now().plusMinutes(67), LocalDateTime.now().plusMinutes(89)));

        add(new TrainStop("Kyiv", LocalDateTime.now().plusMinutes(100), LocalDateTime.now().plusMinutes(115)));
    }};
//    static ArrayList<TrainStop> trainStops2 = new ArrayList<TrainStop>() {{
//
//        add(new TrainStop("Lviv", LocalDateTime.of(2018, 12, 6, 8, 30), LocalDateTime.of(2018, 12, 6, 8, 38)));
//
//        add(new TrainStop("Brody", LocalDateTime.now().plusMinutes(40), LocalDateTime.now().plusMinutes(55)));
//
//        add(new TrainStop("Hogwarts", LocalDateTime.now().plusMinutes(50), LocalDateTime.now().plusMinutes(66)));
//
//        add(new TrainStop("Rivne", LocalDateTime.now().plusMinutes(67), LocalDateTime.now().plusMinutes(89)));
//
//        add(new TrainStop("Kyiv", LocalDateTime.now().plusMinutes(100), LocalDateTime.now().plusMinutes(115)));
//    }};
    {
        trainRoutes.add(new TrainRoute(
                new Train(12,new ArrayList<Carriage>() {{
                    add(new Carriage(1,4));
                }}), trainStops));
//        trainRoutes.add(new TrainRoute(
//                new Train(new ArrayList<Carriage>() {{
//                    add(new Carriage(1));
//                }}), trainStops2));

    }

    List<TrainRoute> findAllAvailables(String from, String to, LocalDateTime dapatureTime) {
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
            boolean isFull = false;
            for (TrainStop trainStop : trainRoute.getTrainStops()) {
                int oneStopCapacity = 0;

                for (Ticket ticket : trainRoute.getTickets()) {
                    if (ticket.getPersonalRoute().contains(trainStop)) {
                        oneStopCapacity++;
                    }
                    if (oneStopCapacity == generalCapacity) {
                        isFull = true;
                    }
                }

            }
            if (!isFull) {
                AvailableTrainRoutes.add(trainRoute);
            }
        }
        return AvailableTrainRoutes;
    }
    TrainRoute findShortestRoute(String from, String to, LocalDateTime localDateTime){
        List<TrainRoute> availableRoutes = findAllAvailables(from,to,localDateTime);
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
    Carriage chooseCarriage(TrainRoute trainRoute){
        for(Carriage carriage :trainRoute.getTrain().getCarriages()){
            System.out.println(carriage);
        }

        int choice = scanner.nextInt();

        for(Carriage carriage: trainRoute.getTrain().getCarriages()){
            if(carriage.getId() == choice){
                return carriage;
            }
        }
        return null;
    }
    Place choosePlace(Carriage carriage, TrainRoute route, LocalDateTime depatureTime){
            List<Place> availablePlaces = new ArrayList<>();
            for (Place place : carriage.getPlaces()) {
                boolean isAvailable = true;
                for(Ticket ticket: route.getTickets()) {
                    if (place.getId() == ticket.getIdPlace()&&carriage.getId() == ticket.getCarriageId()&&
                            ticket.getPersonalRoute().get(0).getDepartureTime().compareTo(depatureTime) <= 0 &&
                            ticket.getPersonalRoute().get(ticket.getPersonalRoute().size()-1).getArrivalTime().compareTo(depatureTime) >= 0) {
                        isAvailable = false;
                        break;
                    }
                }
                if(isAvailable){
                    System.out.println(place);
                    availablePlaces.add(place);
                }
            }

        int chosenId = scanner.nextInt();
        for (Place place:availablePlaces) {
            if(place.getId() == chosenId){
                return place;
            }
        }
        return null;
    }
    TrainStop findTrainStopByName(List<TrainStop> trainStops,String stopName){
        for(TrainStop trainStop: trainStops){
            if(trainStop.getStationName().equals(stopName)){
                return trainStop;
            }
        }
        return null;
    }
    List<TrainStop> generatePersonalRoute(List<TrainStop> trains, String from, String to){
        TrainStop fromTrainStop = findTrainStopByName(trains,from);
        TrainStop toTrainStop = findTrainStopByName(trains,to);
        if(fromTrainStop  == null|| toTrainStop == null){
            return null;
        }
        return trains.subList(trains.indexOf(fromTrainStop),trains.indexOf(toTrainStop)+1);
    }
    void buyTicket(String from, String to,LocalDateTime localDateTime, String firstName, String lastName){
        TrainRoute shortestRoute = findShortestRoute(from, to, localDateTime);
        System.out.println(shortestRoute);
        if(shortestRoute == null) {
            throw new RuntimeException("There's no available  route");
        }
        Carriage chosenCarriage = chooseCarriage(shortestRoute);
        shortestRoute.getTickets().add(new Ticket(
                localDateTime,
                firstName,
                lastName,
                shortestRoute.getTrain().getId(),
                //доробити вибір вибір вагону та місця та генерування особистого маршруту
                chosenCarriage.getId(),
                choosePlace(chosenCarriage,shortestRoute,shortestRoute.getDepatureDate()).getId(),
                new ArrayList<TrainStop>() {{
                    addAll(generatePersonalRoute(shortestRoute.getTrainStops(),from,to));
                }}
        ));
        System.out.println("You successfully bought a ticket");
    }
    public static void main(String[] args) {
        UkrRailway ukrRailway = new UkrRailway();
        boolean isEmpty = ukrRailway.findAllAvailables("Lviv", "Rivne", LocalDateTime.of(2018, 12, 6, 8, 38))
                .isEmpty();
        System.out.println(isEmpty);
        System.out.println(" The fastest way" + ukrRailway.findShortestRoute("Lviv", "Rivne", LocalDateTime.of(2018, 12, 6, 8, 38)));
        while (true) {
            ukrRailway.buyTicket("Lviv", "Rivne", LocalDateTime.of(2018, 12, 6, 8, 38), "Dmytro", "Pavlyshyn");
            isEmpty = ukrRailway.findAllAvailables("Lviv", "Rivne", LocalDateTime.of(2018, 12, 6, 8, 38)).isEmpty();
            System.out.println(ukrRailway.trainRoutes);
            System.out.println(isEmpty);
        }
    }
}
