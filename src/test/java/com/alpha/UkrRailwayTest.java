package com.alpha;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class UkrRailwayTest {
    UkrRailway ukrRailway;

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
    }

    @Test
    public void testBuyTicket() {
        TrainRoute trainRoute = ukrRailway.getTrainRoutes().get(0);
        ukrRailway.buyTicket("Rivne",
                "Kyiv",
                LocalDateTime.of(2018, 12, 6, 5, 0),
                "Ihor",
                "Dronuk",
                trainRoute,
                trainRoute.getCarriages().get(0),
                1
        );

        Assert.assertEquals(trainRoute.getTickets().get(0),
                new Ticket(LocalDateTime.of(2018, 12, 6, 5, 0),
                        "Ihor",
                        "Dronuk",
                        trainRoute.getId(),
                        trainRoute.getCarriages().get(0).getId(),
                        1,
                        TrainStop.subListStop(trainRoute.getTrainStops(), "Rivne", "Kyiv")));
    }

}
