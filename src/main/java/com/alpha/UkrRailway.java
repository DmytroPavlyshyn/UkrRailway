package com.alpha;

import java.time.LocalDateTime;
import java.util.*;

import static com.alpha.TrainStop.*;

public class UkrRailway {
    static Scanner scanner = new Scanner(System.in);
    private List<TrainRoute> trainRoutes = new ArrayList<>();
    private static ArrayList<TrainStop> trainStops = new ArrayList<TrainStop>() {{
        LocalDateTime localDateTime = LocalDateTime.of(2018, 12, 6, 0, 0);
        add(new TrainStop("Lviv", localDateTime, localDateTime.plusHours(1)));

        add(new TrainStop("Brody", localDateTime.plusHours(2), localDateTime.plusHours(3)));

        add(new TrainStop("Rivne", localDateTime.plusHours(4), localDateTime.plusHours(5)));

        add(new TrainStop("Kyiv", localDateTime.plusHours(6), localDateTime.plusHours(7)));
    }};

    {
        trainRoutes.add(new TrainRoute(
                12, new ArrayList<Carriage>() {{
                    add(new Carriage(1, CarriageType.COMPARATMENT, 5));
                }}, trainStops));


    }

    public List<TrainRoute> findAllAvailableARoutes(String from, String to, LocalDateTime dapatureTime) {
        List<TrainRoute> AvailableTrainRoutes = new ArrayList<>();
        for (TrainRoute trainRoute : trainRoutes) {

            int fromIndex = trainRoute.findIndexOfTrainStopByName(from);
            int toIndex = trainRoute.findIndexOfTrainStopByName(to);

            if (fromIndex == -1 || toIndex == -1) {
                continue;
            }
            if (fromIndex >= toIndex) {
                continue;
            }
            int generalCapacity = trainRoute.getGeneralCapacity();
            List<TrainStop> tempRoute = TrainStop.subListStop(trainRoute.getTrainStops(), from, to);
            boolean isFull = false;
            for (TrainStop trainStop : tempRoute) {//trainRoute.getTrainStops()) {
                int oneStopCapacity = 0;
//                Set<TrainStop> trainStops = new HashSet<>();
                for (Ticket ticket : trainRoute.getTickets()) {
                    if (oneStopCapacity == generalCapacity) {
                        isFull = true;
                    }
//                    if(trainStops.size() == generalCapacity){
//                        isFull = true;
//                    }
//                    if (ticket.getPersonalRoute().get(0).getStationName().equals(trainStop.getStationName()) && trainStop.getStationName().equals(tempRoute.get(tempRoute.size() - 1).getStationName())) {
//                        continue;
//                    }
//                    if (ticket.getPersonalRoute().get(ticket.getPersonalRoute().size() - 1).getStationName().equals(trainStop)/* && trainStop.equals(tempRoute.get(0).getStationName())*/) {
//                        continue;
//                    }
                    if (ticket.getPersonalRoute().subList(0, ticket.getPersonalRoute().size()-2).contains(trainStop)) {
                        oneStopCapacity++;
                    }

                }

            }
            if (!isFull) {
                AvailableTrainRoutes.add(trainRoute);
            }
        }
        return AvailableTrainRoutes;
    }

    public TrainRoute findShortestRoute(String from, String to, LocalDateTime localDateTime) {
        try {
            List<TrainRoute> availableRoutes = findAllAvailableARoutes(from, to, localDateTime);

            TrainRoute shortestRoute = null;
            int minimalDistance = -1;
        if (availableRoutes.isEmpty()) {
            throw new RuntimeException("There\'s no available routes");
        }

            for (TrainRoute trainRoute : availableRoutes) {
                if (minimalDistance < 0) {
                    minimalDistance = trainRoute.findIndexOfTrainStopByName(to) - trainRoute.findIndexOfTrainStopByName(from);
                    shortestRoute = trainRoute;
                } else if (trainRoute.findIndexOfTrainStopByName(to) - trainRoute.findIndexOfTrainStopByName(from) < minimalDistance) {
                    minimalDistance = trainRoute.findIndexOfTrainStopByName(to) - trainRoute.findIndexOfTrainStopByName(from);
                    shortestRoute = trainRoute;
                }

            }
            return shortestRoute;
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    private Carriage chooseCarriage(TrainRoute trainRoute) {
        for (Carriage carriage : trainRoute.getCarriages()) {
            System.out.println(carriage);
        }
        int choice = scanner.nextInt();
        for (Carriage carriage : trainRoute.getCarriages()) {
            if (carriage.getId() == choice) {
                return carriage;
            }
        }
        throw new RuntimeException("There\'s no such an carriage");
    }

    private Integer choosePlace(Carriage carriage, TrainRoute route, String from, String to) {
        List<TrainStop> personalRoute = subListStop(route.getTrainStops(), from, to);
        List<Integer> availablePlaces = new ArrayList<>();
        for (int placeNumber = 1; placeNumber <= carriage.getCapacity(); placeNumber++) {
            boolean isAvailable = true;
            for (TrainStop trainStop : personalRoute) {
                for (Ticket ticket : route.getTickets()) {

                    if ((trainStop.equals(personalRoute.get(0)) && trainStop.getStationName().equals(ticket.getPersonalRoute().get(ticket.getPersonalRoute().size() - 1).getStationName())) ||
                            (trainStop.equals(personalRoute.get(personalRoute.size() - 1)) && trainStop.getStationName().equals(ticket.getPersonalRoute().get(0).getStationName()))) {
                        continue;
                    }
                    if (carriage.getId() == ticket.getCarriageId() && placeNumber == ticket.getIdPlace() && ticket.getPersonalRoute().contains(trainStop)) {
                        isAvailable = false;
                    }
                }
            }
            if (isAvailable) {
                System.out.println("Place: " + placeNumber);
                availablePlaces.add(placeNumber);
            }
        }
        int chosenId = scanner.nextInt();
        for (Integer place : availablePlaces) {
            if (place == chosenId) {
                return place;
            }
        }
        throw new RuntimeException("There\'s no  such an available place");
    }

    void buyTicket(String from, String to, LocalDateTime localDateTime, String firstName, String lastName) {
        try {
            TrainRoute shortestRoute = findShortestRoute(from, to, localDateTime);

            Carriage chosenCarriage = chooseCarriage(shortestRoute);
            List<TrainStop> personalRoute = subListStop(shortestRoute.getTrainStops(), from, to);
            shortestRoute.getTickets().add(new Ticket(
                    localDateTime,
                    firstName,
                    lastName,
                    shortestRoute.getId(),
                    chosenCarriage.getId(),
                    choosePlace(chosenCarriage, shortestRoute, from, to),
                    new ArrayList<TrainStop>() {{
                        addAll(personalRoute);
                    }}
            ));
            System.out.println("You successfully bought a ticket");
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    void buyTicket(String from, String to, LocalDateTime localDateTime, String firstName, String lastName, TrainRoute trainRoute) {

        Carriage chosenCarriage = chooseCarriage(trainRoute);
        List<TrainStop> personalRoute = subListStop(trainRoute.getTrainStops(), from, to);
        trainRoute.getTickets().add(new Ticket(
                localDateTime,
                firstName,
                lastName,
                trainRoute.getId(),
                chosenCarriage.getId(),
                choosePlace(chosenCarriage, trainRoute, from, to),
                new ArrayList<TrainStop>() {{
                    addAll(personalRoute);
                }}
        ));
        System.out.println("You successfully bought a ticket");
    }
}
