package com.alpha;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Test {             ////якщо тут запускати то тільки дату
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

//        boolean isEmpty;
//        while (true) {
//            try {
//                ukrRailway.buyTicket("Rivne", "Kyiv", LocalDateTime.of( 2018, 12, 6, 5, 0), "12", "12");
//                ukrRailway.buyTicket("Lviv", "Brody", LocalDateTime.of( 2018, 12, 6, 1, 0), "12", "12");
//                ukrRailway.buyTicket("Brody", "Rivne",LocalDateTime.of( 2018, 12, 6, 3, 0), "12", "12");
//                ukrRailway.buyTicket("Lviv", "Rivne", LocalDateTime.of( 2018, 12, 6, 1, 0), "12", "12");
//                ukrRailway.buyTicket("Rivne", "Kyiv", LocalDateTime.of( 2018, 12, 6, 5, 0), "12", "12");
//                ukrRailway.buyTicket("Rivne", "Kyiv", LocalDateTime.of( 2018, 12, 6, 5, 0), "12", "12");
//                ukrRailway.buyTicket("Lviv", "Rivne", LocalDateTime.of( 2018, 12, 6, 1, 0), "12", "12");
//                ukrRailway.buyTicket("Lviv", "Kyiv",  LocalDateTime.of( 2018, 12, 6, 1, 0), "12", "12");
//            } catch (RuntimeException e) {
//                System.err.println(e);
//                break;
//
//            }
//        }
        List<TrainRoute> trainRoutes = new ArrayList<>();

        ArrayList<TrainStop> trainStops = new ArrayList<TrainStop>() {{
            LocalDateTime localDateTime = LocalDateTime.of(2018, 12, 6, 0, 0);
            add(new TrainStop("Lviv", localDateTime, localDateTime.plusHours(1)));

            add(new TrainStop("Brody", localDateTime.plusHours(2), localDateTime.plusHours(3)));

            add(new TrainStop("Rivne", localDateTime.plusHours(4), localDateTime.plusHours(5)));

            add(new TrainStop("Kyiv", localDateTime.plusHours(6), localDateTime.plusHours(7)));
        }};

        {
            trainRoutes.add(new TrainRoute(
                    12, new ArrayList<Carriage>() {{
                add(new Carriage(1, CarriageType.SV));
                add(new Carriage(2, CarriageType.SV));

            }}, trainStops));


        }
        UkrRailway ukrRailway = new UkrRailway(trainRoutes);

        ukrRailway.buyTicket("Rivne",
                "Kyiv",
                LocalDateTime.of(2018, 12, 6, 5, 0),
                "Ihor",
                "Dronuk",
                trainRoutes.get(0),
                trainRoutes.get(0).getCarriages().get(0),
                1
                );
        System.out.println("Lol");
    }
}