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

    public List<TrainRoute> getTrainRoutes() {
        return trainRoutes;
    }

    public void setTrainRoutes(List<TrainRoute> trainRoutes) {
        this.trainRoutes = trainRoutes;
    }

    public void showRoute() {
        System.out.println(trainRoutes);
    }

    public List<TrainRoute> findAvailableARoutes(String from, String to, LocalDateTime dapatureTime) {
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
                    if (oneStopCapacity == generalCapacity) {
                        isFull = true;
                    }

                    if (ticket.getPersonalRoute().subList(0, ticket.getPersonalRoute().size() - 2).contains(trainStop)) {
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
            List<TrainRoute> availableRoutes = findAvailableARoutes(from, to, localDateTime);

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

    private Carriage chooseCarriage(TrainRoute trainRoute, String from, String to) {
        List<Carriage> carriages = getAvailableCarriages(trainRoute, from, to);/*new ArrayList<>();
        for (Carriage carriage : trainRoute.getCarriages()) {
            if(getAvailablePlaces(carriage,trainRoute,from,to).isEmpty()){
                continue;
            }
            carriages.add(carriage);
            System.out.println(carriage);
        }*/
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

    private Integer choosePlace(Carriage carriage, TrainRoute route, String from, String to) {
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

            TrainRoute shortestRoute = findShortestRoute(from, to, localDateTime);

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

    private List<Carriage> getAvailableCarriages(TrainRoute trainRoute, String from, String to) {
        List<Carriage> carriages = new ArrayList<>();
        for (Carriage carriage : trainRoute.getCarriages()) {
            if (getAvailablePlaces(carriage, trainRoute, from, to).isEmpty()) {
                continue;
            }
            carriages.add(carriage);
        }
        return carriages;
    }

    private List<Integer> getAvailablePlaces(Carriage carriage, TrainRoute route, String from, String to) {
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

    void buyTicket(String from, String to, LocalDateTime localDateTime, String firstName, String lastName, TrainRoute trainRoute, Carriage carriage, int placeId) {
        List<TrainRoute> availableRoutes = findAvailableARoutes(from, to, localDateTime);
        if (availableRoutes.isEmpty() || !availableRoutes.contains(trainRoute)) {
            throw new RuntimeException("There\'s no available  routes");
        }
        if (!getAvailableCarriages(trainRoute, from, to).contains(carriage)) {
            throw new RuntimeException("There\'s no available  such carriage");
        }

        if (!getAvailablePlaces(carriage, trainRoute, from, to).contains(placeId)) {
            throw new RuntimeException("There\'s no available  such place");
        }
        List<TrainStop> trainStop = subListStop(trainRoute.getTrainStops(),from,to);
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
}
