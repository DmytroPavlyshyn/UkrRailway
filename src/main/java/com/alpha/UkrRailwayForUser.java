package com.alpha;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

import static com.alpha.TrainStop.subListOfStops;

public class UkrRailwayForUser {
    static Scanner scanner = new Scanner(System.in);
    UkrRailway ukrRailway;

    public UkrRailwayForUser(UkrRailway ukrRailway) {
        this.ukrRailway = ukrRailway;
    }


    Carriage chooseCarriage(TrainRoute trainRoute, String from, String to) {
        List<Carriage> carriages = ukrRailway.getAvailableCarriages(trainRoute, from, to);
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
        List<Integer> availablePlaces = ukrRailway.getAvailablePlaces(carriage, route, from, to);
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

            TrainRoute shortestRoute = ukrRailway.getShortestRoute(from, to, localDateTime);

            Carriage chosenCarriage = chooseCarriage(shortestRoute, from, to);
            int chosenPlace = choosePlace(chosenCarriage, shortestRoute, from, to);
            List<TrainStop> personalRoute = subListOfStops(shortestRoute.getTrainStops(), from, to);
            shortestRoute.getTickets().add(new Ticket(
                    localDateTime,
                    firstName,
                    lastName,
                    shortestRoute.getId(),
                    chosenCarriage.getId(),
                    chosenPlace,
                    personalRoute.get(0),
                    personalRoute.get(personalRoute.size() - 1)
            ));
            System.out.println("You successfully bought a ticket");
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }
}
