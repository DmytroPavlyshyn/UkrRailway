package com.alpha;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.assertj.core.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class UkrRailwayTest {
    final String[] cities = {"Lviv", "Brody", "Rivne", "Kyiv"};

    UkrRailway ukrRailway;
    TrainRoute trainRoute;
    List<Ticket> tickets;

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


    @Before
    public void setUp() {
        ukrRailway = new UkrRailway(new ArrayList<TrainRoute>() {{
            add(generateTrainRoute());
        }});
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
    public void testButTicketBetweenTwoTicketsRouts() {
        ukrRailway.buyTicket(tickets.get(2));
        ukrRailway.buyTicket(tickets.get(0));
        ukrRailway.buyTicket(tickets.get(1));
        Assert.assertEquals(trainRoute.getTickets().get(2),
                tickets.get(1));
    }
    @Test
    public void testButTicketInEndOfRoute() {
        ukrRailway.buyTicket(tickets.get(4));
        ukrRailway.buyTicket(tickets.get(3));
        Assert.assertEquals(trainRoute.getTickets().get(1),tickets.get(3));
    }
    @Test
    public void testButTicketInStartOfRoute() {
        ukrRailway.buyTicket(tickets.get(6));
        ukrRailway.buyTicket(tickets.get(5));
        Assert.assertEquals(trainRoute.getTickets().get(1),tickets.get(5));
    }
    @Test
    public void testFindAllAvailableIsNotEmpty(){
       // ukrRailway.getAvailableARoutes(tickets.get(7).getPersonalRoute().get(0).getStationName(),)
    }
    public List<Ticket> generateTickets() {
        List<Ticket> tickets = new ArrayList<>();
        tickets.add(new Ticket(LocalDateTime.of(2018, 12, 6, 1, 0),
                "f1",
                "l1",
                trainRoute.getId(),
                trainRoute.getCarriages().get(0).getId(),
                1,
                TrainStop.subListStop(trainRoute.getTrainStops(), "Lviv", "Brody")));
        tickets.add(new Ticket(LocalDateTime.of(2018, 12, 6, 3, 0),
                "f2",
                "l2",
                trainRoute.getId(),
                trainRoute.getCarriages().get(0).getId(),
                1,
                TrainStop.subListStop(trainRoute.getTrainStops(), "Brody", "Rivne")));
        tickets.add(new Ticket(LocalDateTime.of(2018, 12, 6, 5, 0),
                "f3",
                "l3",
                trainRoute.getId(),
                trainRoute.getCarriages().get(0).getId(),
                1,
                TrainStop.subListStop(trainRoute.getTrainStops(), "Rivne", "Kyiv")));

        tickets.add(new Ticket(LocalDateTime.of(2018, 12, 6, 1, 0),
                "f4",
                "l4",
                trainRoute.getId(),
                trainRoute.getCarriages().get(0).getId(),
                1,
                TrainStop.subListStop(trainRoute.getTrainStops(), "Lviv", "Rivne")));
        tickets.add(new Ticket(LocalDateTime.of(2018, 12, 6, 5, 0),
                "f5",
                "l5",
                trainRoute.getId(),
                trainRoute.getCarriages().get(0).getId(),
                1,
                TrainStop.subListStop(trainRoute.getTrainStops(), "Rivne", "Kyiv")));

        tickets.add(new Ticket(LocalDateTime.of(2018, 12, 6, 1, 0),
                "f6",
                "l6",
                trainRoute.getId(),
                trainRoute.getCarriages().get(0).getId(),
                1,
                TrainStop.subListStop(trainRoute.getTrainStops(), "Lviv", "Brody")));
        tickets.add(new Ticket(LocalDateTime.of(2018, 12, 6, 5, 0),
                "f7",
                "l7",
                trainRoute.getId(),
                trainRoute.getCarriages().get(0).getId(),
                1,
                TrainStop.subListStop(trainRoute.getTrainStops(), "Brody", "Kyiv")));

        tickets.add(new Ticket(LocalDateTime.of(2018, 12, 6, 1, 0),
                "f8",
                "l8",
                trainRoute.getId(),
                trainRoute.getCarriages().get(0).getId(),
                1,
                TrainStop.subListStop(trainRoute.getTrainStops(), "Lviv", "Kyiv")));

        return tickets;
    }

}
