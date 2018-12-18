package com.alpha;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static com.alpha.TrainStop.subListOfStops;

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

    Carriage getCarriageById(TrainRoute trainRoute, int id) {
        for (Carriage carriage : trainRoute.getCarriages()) {
            if (carriage.getId() == id) {
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
        List<TrainStop> newPersonalStops = subListOfStops(route.getTrainStops(), from, to);
        List<Integer> availablePlaces = new ArrayList<>();
        for (int placeNumber = 1; placeNumber <= carriage.getCapacity(); placeNumber++) {
            boolean isAvailable = true;


            for (TrainStop trainStop : newPersonalStops) {
                for (Ticket ticket : route.getTickets()) {
                    List<TrainStop> ticketStops = getTrainRouteById(ticket.getTrainRouteId()).getTrainStops();

                    List<TrainStop> ticketStopsMatchedNewPersonalStops = TrainStop.subListOfStops(ticketStops, ticket.getStart().getStationName(), ticket.getEnd().getStationName());

                    if (trainStop.equals(newPersonalStops.get(0)) && trainStop.equals(ticketStopsMatchedNewPersonalStops.get(ticketStopsMatchedNewPersonalStops.size() - 1))) {
                        continue;
                    }

                    if (trainStop.equals(newPersonalStops.get(newPersonalStops.size() - 1)) && trainStop.equals(ticketStopsMatchedNewPersonalStops.get(0))) {
                        continue;
                    }

                    if (carriage.getId() == ticket.getCarriageId() && placeNumber == ticket.getIdPlace() && ticketStopsMatchedNewPersonalStops.contains(trainStop)) {
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

    boolean isCorrectRoute(int fromIndex, int toIndex) {
        if (fromIndex == -1 || toIndex == -1) {
            return false;
        }
        if (fromIndex >= toIndex) {
            return false;
        }
        return true;
    }

    List<TrainRoute> getAvailableRoutes(String from, String to, LocalDateTime dapatureTime) {
        List<TrainRoute> AvailableTrainRoutes = new ArrayList<>();
        for (TrainRoute trainRoute : trainRoutes) {

            int fromIndex = trainRoute.findIndexOfTrainStopByName(from);
            int toIndex = trainRoute.findIndexOfTrainStopByName(to);
            if (!isCorrectRoute(fromIndex, toIndex)) {
                continue;
            }

            int generalCapacity = trainRoute.getGeneralCapacity();
            List<TrainStop> tempRoute = TrainStop.subListOfStops(trainRoute.getTrainStops(), from, to);
            boolean isFull = false;
            for (TrainStop trainStop : tempRoute) {
                int oneStopCapacity = 0;
                for (Ticket ticket : trainRoute.getTickets()) {
                    List<TrainStop> ticketTrainStops = getTrainRouteById(ticket.getTrainRouteId()).getTrainStops();
                    List<TrainStop> trainStops = TrainStop.subListOfStops(ticketTrainStops, ticket.getStart().getStationName(), ticket.getEnd().getStationName());

                    if (trainStops.subList(0, trainStops.size() - 1).contains(trainStop)) {
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
        List<TrainStop> trainStop = subListOfStops(trainRoute.getTrainStops(), from, to);
        trainRoute.getTickets().add(new Ticket(
                localDateTime,
                firstName,
                lastName,
                trainRoute.getId(),
                carriage.getId(),
                placeId,
                trainStop.get(0),
                trainStop.get(trainStop.size() - 1)
        ));
    }

    void buyTicket(Ticket ticket) {
        TrainRoute trainRoute = getTrainRouteById(ticket.getTrainRouteId());
        Carriage carriage = getCarriageById(trainRoute, ticket.getCarriageId());
        buyTicket(ticket.getStart().getStationName(),
                ticket.getEnd().getStationName(),
                ticket.getStart().getDepartureTime(),
                ticket.getFirstName(),
                ticket.getLastName(),
                trainRoute,
                carriage,
                ticket.getIdPlace()
        );
    }
}
