package com.alpha;

import org.junit.*;
import org.junit.Test;

import java.lang.reflect.Array;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class UkrRailwayTest2 {
    final String[] cities = {"Lviv", "Brody", "Rivne", "Kyiv"};

    UkrRailway ukrRailway;
    TrainRoute trainRoute;
    List<Ticket> tickets;

    public List<TrainRoute> generateTrainRoutes() {
        List<TrainRoute> trainRoutes = new ArrayList<>();
        ArrayList<TrainStop> trainStops1 = new ArrayList<TrainStop>() {{
            LocalDateTime localDateTime = LocalDateTime.of(2018, 12, 6, 0, 0);
            add(new TrainStop("Lviv", localDateTime, localDateTime.plusHours(1)));

            add(new TrainStop("Brody", localDateTime.plusHours(2), localDateTime.plusHours(3)));

            add(new TrainStop("Rivne", localDateTime.plusHours(4), localDateTime.plusHours(5)));

            add(new TrainStop("Kyiv", localDateTime.plusHours(6), localDateTime.plusHours(7)));
        }};
        ArrayList<TrainStop> trainStops2 = new ArrayList<TrainStop>() {{
            LocalDateTime localDateTime = LocalDateTime.of(2018, 12, 6, 0, 0);
            add(new TrainStop("Lviv", localDateTime, localDateTime.plusHours(1)));

            add(new TrainStop("Rivne", localDateTime.plusHours(4), localDateTime.plusHours(5)));

            add(new TrainStop("Kyiv", localDateTime.plusHours(6), localDateTime.plusHours(7)));
        }};
        trainRoutes.add(new TrainRoute(1, new ArrayList<Carriage>() {{
            add(new Carriage(1, CarriageType.SV));
            add(new Carriage(2, CarriageType.SV));

        }}, trainStops1));
        trainRoutes.add(new TrainRoute(2, new ArrayList<Carriage>() {{
            add(new Carriage(1, CarriageType.SV));
            add(new Carriage(2, CarriageType.SV));

        }}, trainStops2));
        return trainRoutes;
    }


    @Before
    public void setUp() {
        ukrRailway = new UkrRailway(generateTrainRoutes());
        trainRoute = ukrRailway.getTrainRoutes().get(0);
        tickets = generateTickets();
    }

    @Test
    public void testBuyTicketAllRoute() {

        ukrRailway.buyTicket(tickets.get(7)
        );

        Assert.assertEquals(trainRoute.getTickets().get(0), tickets.get(7));

    }

    @Test
    public void testBuyTicketBetweenTwoTicketsRouts() {
        ukrRailway.buyTicket(tickets.get(2));
        ukrRailway.buyTicket(tickets.get(0));
        ukrRailway.buyTicket(tickets.get(1));
        Assert.assertEquals(trainRoute.getTickets().get(2),
                tickets.get(1));
    }

    @Test
    public void testBuyTicketInEndOfRoute() {
        ukrRailway.buyTicket(tickets.get(4));
        ukrRailway.buyTicket(tickets.get(3));

        Assert.assertEquals(trainRoute.getTickets().get(1), tickets.get(3));
    }

    @Test
    public void testBuyTicketInStartOfRoute() {
        ukrRailway.buyTicket(tickets.get(6));
        ukrRailway.buyTicket(tickets.get(5));
        Assert.assertEquals(trainRoute.getTickets().get(1), tickets.get(5));
    }

    @Test
    public void testGetAvailableRoutesIsNotEmpty() {
        Assert.assertTrue(ukrRailway.getAvailableRoutes(cities[0],
                cities[3], tickets.get(7).getPersonalRoute().get(0).getDepartureTime()).contains(trainRoute));
    }
    @Test
    public void testGetShortestRoute(){
        Assert.assertEquals(ukrRailway.getTrainRoutes().get(1),ukrRailway.getShortestRoute(cities[0],cities[3],tickets.get(7).getPersonalRoute().get(0).getDepartureTime()));
    }
    @Test
    public void testGetAvailableRoutesIsEmpty() {
        ukrRailway.buyTicket(tickets.get(2));
        ukrRailway.buyTicket(tickets.get(0));
        ukrRailway.buyTicket(tickets.get(1));
        ukrRailway.buyTicket(tickets.get(4));
        ukrRailway.buyTicket(tickets.get(3));
        ukrRailway.buyTicket(tickets.get(6));
        ukrRailway.buyTicket(tickets.get(5));
        ukrRailway.buyTicket(tickets.get(7));
        List<Carriage> carriages = ukrRailway.getTrainRoutes().get(0).getCarriages();
        carriages.remove(carriages.get(1));
        List<TrainRoute> trainRoutes = ukrRailway.getTrainRoutes();
        trainRoutes.remove(trainRoutes.get(1));
        Assert.assertTrue(ukrRailway.getAvailableRoutes(cities[0], cities[3], tickets.get(7).getPersonalRoute().get(0).getDepartureTime()).isEmpty());
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

}
