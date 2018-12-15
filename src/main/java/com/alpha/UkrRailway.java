package com.alpha;


import java.time.LocalDateTime;
import java.util.*;

import static com.alpha.TrainStop.*;

public class UkrRailway {
    static Scanner scanner = new Scanner(System.in);
    private List<TrainRoute> trainRoutes;

    public UkrRailway(List<TrainRoute> trainRoutes) {
        this.trainRoutes = trainRoutes;
    }

    TrainRoute getTrainRouteById(int id) {
        for (TrainRoute trainRoute : trainRoutes) {
            if (id == trainRoute.getId()) {
                return trainRoute;
            }
        }
        return null;
    }
    Carriage getCarriageById(TrainRoute trainRoute, int id){
        for(Carriage carriage: trainRoute.getCarriages()){
            if(carriage.getId() == id){
                return carriage;
            }
        }
        return null;
    }

    List<TrainRoute> getTrainRoutes() {
        return trainRoutes;
    }

    public void showRoutes() {
        System.out.println(trainRoutes);
    }

    List<Integer> getAvailablePlaces(Carriage carriage, TrainRoute route, String from, String to) {
        List<TrainStop> personalRoute = subListStop(route.getTrainStops(), from, to);
        List<Integer> availablePlaces = new ArrayList<>();
        for (int placeNumber = 1; placeNumber <= carriage.getCapacity(); placeNumber++) {
            boolean isAvailable = true;
            for (TrainStop trainStop : personalRoute) {
                for (Ticket ticket : route.getTickets()) {

                    if ((trainStop.equals(personalRoute.get(0)) && trainStop.equals(ticket.getPersonalRoute().get(ticket.getPersonalRoute().size() - 1))) ||
                            (trainStop.equals(personalRoute.get(personalRoute.size() - 1)) && trainStop.equals(ticket.getPersonalRoute().get(0)))) {
                        continue;
                    }
                    if (carriage.getId() == ticket.getCarriageId() && placeNumber == ticket.getIdPlace() && ticket.getPersonalRoute().contains(trainStop)) {
                        isAvailable = false;
                    }
                }
            }
            if (isAvailable) {
                availablePlaces.add(placeNumber);
            }
        }
        return availablePlaces;
    }

    List<Carriage> getAvailableCarriages(TrainRoute trainRoute, String from, String to) {
        List<Carriage> carriages = new ArrayList<>();
        for (Carriage carriage : trainRoute.getCarriages()) {
            if (getAvailablePlaces(carriage, trainRoute, from, to).isEmpty()) {
                continue;
            }
            carriages.add(carriage);
        }
        return carriages;
    }

    List<TrainRoute> getAvailableRoutes(String from, String to, LocalDateTime dapatureTime) {
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
            for (TrainStop trainStop : tempRoute) {
                int oneStopCapacity = 0;
                for (Ticket ticket : trainRoute.getTickets()) {


                    if (ticket.getPersonalRoute().subList(0, ticket.getPersonalRoute().size() - 1).contains(trainStop)) {
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

    TrainRoute getShortestRoute(String from, String to, LocalDateTime localDateTime) {
        try {
            List<TrainRoute> availableRoutes = getAvailableRoutes(from, to, localDateTime);

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

    Carriage chooseCarriage(TrainRoute trainRoute, String from, String to) {
        List<Carriage> carriages = getAvailableCarriages(trainRoute, from, to);
        if (carriages.isEmpty()) {
            throw new RuntimeException("There\'s no available carriages");
        }
        System.out.println(carriages);
        int choice = scanner.nextInt();
        for (Carriage carriage : trainRoute.getCarriages()) {
            if (carriage.getId() == choice) {
                return carriage;
            }
        }
        throw new RuntimeException("There\'s no such an carriage");
    }

    Integer choosePlace(Carriage carriage, TrainRoute route, String from, String to) {
        List<Integer> availablePlaces = getAvailablePlaces(carriage, route, from, to);
        if (availablePlaces.isEmpty()) {
            throw new RuntimeException("There\'s no  available places");
        }
        System.out.println(availablePlaces);
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

            TrainRoute shortestRoute = getShortestRoute(from, to, localDateTime);

            Carriage chosenCarriage = chooseCarriage(shortestRoute, from, to);
            int chosenPlace = choosePlace(chosenCarriage, shortestRoute, from, to);
            List<TrainStop> personalRoute = subListStop(shortestRoute.getTrainStops(), from, to);
            shortestRoute.getTickets().add(new Ticket(
                    localDateTime,
                    firstName,
                    lastName,
                    shortestRoute.getId(),
                    chosenCarriage.getId(),
                    chosenPlace,
                    new ArrayList<TrainStop>() {{
                        addAll(personalRoute);
                    }}
            ));
            System.out.println("You successfully bought a ticket");
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    void buyTicket(String from, String to, LocalDateTime localDateTime, String firstName, String lastName, TrainRoute trainRoute, Carriage carriage, int placeId) {
        List<TrainRoute> availableRoutes = getAvailableRoutes(from, to, localDateTime);
        if (availableRoutes.isEmpty() || !availableRoutes.contains(trainRoute)) {
            throw new RuntimeException("There\'s no available  routes");
        }
        if (!getAvailableCarriages(trainRoute, from, to).contains(carriage)) {
            throw new RuntimeException("There\'s no available  such carriage");
        }

        if (!getAvailablePlaces(carriage, trainRoute, from, to).contains(placeId)) {
            throw new RuntimeException("There\'s no available  such place");
        }
        List<TrainStop> trainStop = subListStop(trainRoute.getTrainStops(), from, to);
        trainRoute.getTickets().add(new Ticket(
                localDateTime,
                firstName,
                lastName,
                trainRoute.getId(),
                carriage.getId(),
                placeId,
                trainStop
        ));
    }

    void buyTicket(Ticket ticket) {
        TrainRoute trainRoute = getTrainRouteById(ticket.getTrainRouteId());
        Carriage carriage = getCarriageById(trainRoute,ticket.getCarriageId());
        buyTicket(ticket.getPersonalRoute().get(0).getStationName(),
                ticket.getPersonalRoute().get(ticket.getPersonalRoute().size() - 1).getStationName(),
                ticket.getPersonalRoute().get(0).getDepartureTime(),
                ticket.getFirstName(),
                ticket.getLastName(),
                trainRoute,
                carriage,
                ticket.getIdPlace()
                );
    }
}
