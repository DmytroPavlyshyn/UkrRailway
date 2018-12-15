package com.alpha;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Test {
    final String[] cities = {"Lviv", "Brody", "Rivne", "Kyiv"};

    UkrRailway ukrRailway;
    TrainRoute trainRoute;
    static List<Ticket> tickets;

    public TrainRoute generateTrainRoute() {
        ArrayList<TrainStop> trainStops = new ArrayList<TrainStop>() {{
            LocalDateTime localDateTime = LocalDateTime.of(2018, 12, 6, 0, 0);
            add(new TrainStop("Lviv", localDateTime, localDateTime.plusHours(1)));

            add(new TrainStop("Brody", localDateTime.plusHours(2), localDateTime.plusHours(3)));

            add(new TrainStop("Rivne", localDateTime.plusHours(4), localDateTime.plusHours(5)));

            add(new TrainStop("Kyiv", localDateTime.plusHours(6), localDateTime.plusHours(7)));
        }};
        return new TrainRoute(1, new ArrayList<Carriage>() {{
            add(new Carriage(1, CarriageType.SV));
            add(new Carriage(2, CarriageType.SV));

        }}, trainStops);

    }


    public void setUp() {
        ukrRailway = new UkrRailway(new ArrayList<TrainRoute>() {{
            add(generateTrainRoute());
        }});
        trainRoute = ukrRailway.getTrainRoutes().get(0);
        tickets = generateTickets();
    }

    public void testBuyTicketAllRoute() {

        ukrRailway.buyTicket(tickets.get(7)
        );


    }

    public void testBuyTicketBetweenTwoTicketsRouts() {
        ukrRailway.buyTicket(tickets.get(2));
        ukrRailway.buyTicket(tickets.get(0));
        ukrRailway.buyTicket(tickets.get(1));
    }
    public void testBuyTicketInEndOfRoute() {
        ukrRailway.buyTicket(tickets.get(4));
        ukrRailway.buyTicket(tickets.get(3));

    }
    public void testBuyTicketInStartOfRoute() {
        ukrRailway.buyTicket(tickets.get(6));
        ukrRailway.buyTicket(tickets.get(5));
    }
    public void testGetAvailableRoutesIsNotEmpty(){
    }

    public void testGetAvailableRoutesIsEmpty() {
        ukrRailway.buyTicket(tickets.get(2));
        ukrRailway.buyTicket(tickets.get(0));
        ukrRailway.buyTicket(tickets.get(1));
        ukrRailway.buyTicket(tickets.get(4));
        ukrRailway.buyTicket(tickets.get(3));
        ukrRailway.buyTicket(tickets.get(6));
        ukrRailway.buyTicket(tickets.get(5));
        ukrRailway.buyTicket(tickets.get(7));
    }
    public List<Ticket> generateTickets() {
        List<Ticket> tickets = new ArrayList<>();
        tickets.add(new Ticket(LocalDateTime.of(2018, 12, 6, 1, 0),
                "f1",
                "l1",
                trainRoute.getId(),
                trainRoute.getCarriages().get(0).getId(),
                1,
                TrainStop.subListStop(trainRoute.getTrainStops(), cities[0], cities[1])));
        tickets.add(new Ticket(LocalDateTime.of(2018, 12, 6, 3, 0),
                "f2",
                "l2",
                trainRoute.getId(),
                trainRoute.getCarriages().get(0).getId(),
                1,
                TrainStop.subListStop(trainRoute.getTrainStops(), cities[1], cities[2])));
        tickets.add(new Ticket(LocalDateTime.of(2018, 12, 6, 5, 0),
                "f3",
                "l3",
                trainRoute.getId(),
                trainRoute.getCarriages().get(0).getId(),
                1,
                TrainStop.subListStop(trainRoute.getTrainStops(), cities[2], cities[3])));

        tickets.add(new Ticket(LocalDateTime.of(2018, 12, 6, 1, 0),
                "f4",
                "l4",
                trainRoute.getId(),
                trainRoute.getCarriages().get(0).getId(),
                2,
                TrainStop.subListStop(trainRoute.getTrainStops(), cities[0], cities[2])));
        tickets.add(new Ticket(LocalDateTime.of(2018, 12, 6, 5, 0),
                "f5",
                "l5",
                trainRoute.getId(),
                trainRoute.getCarriages().get(0).getId(),
                2,
                TrainStop.subListStop(trainRoute.getTrainStops(), cities[2], cities[3])));

        tickets.add(new Ticket(LocalDateTime.of(2018, 12, 6, 1, 0),
                "f6",
                "l6",
                trainRoute.getId(),
                trainRoute.getCarriages().get(0).getId(),
                3,
                TrainStop.subListStop(trainRoute.getTrainStops(), cities[0], cities[1])));
        tickets.add(new Ticket(LocalDateTime.of(2018, 12, 6, 5, 0),
                "f7",
                "l7",
                trainRoute.getId(),
                trainRoute.getCarriages().get(0).getId(),
                3,
                TrainStop.subListStop(trainRoute.getTrainStops(), cities[1], cities[3])));

        tickets.add(new Ticket(LocalDateTime.of(2018, 12, 6, 1, 0),
                "f8",
                "l8",
                trainRoute.getId(),
                trainRoute.getCarriages().get(0).getId(),
                4,
                TrainStop.subListStop(trainRoute.getTrainStops(), cities[0], cities[3])));

        return tickets;
    }

    public static void main(String[] args) {
        Test test = new Test();
        test.setUp();
        test.testGetAvailableRoutesIsEmpty();
        List<Carriage> carriages = test.ukrRailway.getTrainRoutes().get(0).getCarriages();
        carriages.remove(carriages.get(1));
        System.out.println(test.ukrRailway.getAvailableRoutes("Lviv","Kyiv",tickets.get(7).getPersonalRoute().get(0).getDepartureTime()).isEmpty());
        System.out.println(test.ukrRailway.getShortestRoute("Lviv","Kyiv",tickets.get(7).getPersonalRoute().get(0).getDepartureTime()));
        test.ukrRailway.buyTicket("Lviv","Kyiv",tickets.get(7).getPersonalRoute().get(0).getDepartureTime(),"12","12");
    }
}
