package com.alpha;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Demo { //якщо тут запускати то треба вводити все вручну
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        ArrayList<TrainStop> trainStops = new ArrayList<TrainStop>() {{
            LocalDateTime localDateTime = LocalDateTime.of(2018, 12, 6, 0, 0);
            add(new TrainStop("Lviv", localDateTime, localDateTime.plusHours(1)));

            add(new TrainStop("Brody", localDateTime.plusHours(2), localDateTime.plusHours(3)));

            add(new TrainStop("Rivne", localDateTime.plusHours(4), localDateTime.plusHours(5)));

            add(new TrainStop("Kyiv", localDateTime.plusHours(6), localDateTime.plusHours(7)));
        }};


        UkrRailway ukrRailway = new UkrRailway(Arrays.asList(new TrainRoute(1, new ArrayList<Carriage>() {{
            add(new Carriage(1, CarriageType.SV));
        }}, trainStops)));
        boolean isEmpty;
        while (true) {
            try {
                ukrRailway.showRoutes();
                System.out.println("Enter month: ");
                Integer month = scanner.nextInt();
                System.out.println("Enter day");
                Integer day = scanner.nextInt();
                System.out.println("Enter hour");
                Integer hour = scanner.nextInt();
                scanner.nextLine();
                System.out.println("Enter city of departure: ");
                String from = scanner.nextLine();
                System.out.println("Enter city of arrival: ");
                String to = scanner.nextLine();
                System.out.println("Enter first name: ");
                String firstName = scanner.nextLine();
                System.out.println("Enter last name: ");
                String lastName = scanner.nextLine();
                System.out.println(ukrRailway.getShortestRoute(from, to, LocalDateTime.of(2018, month, day, hour, 0)));
                ukrRailway.buyTicket(from, to, LocalDateTime.of(2018, month, day, hour, 0), firstName, lastName);

            } catch (Exception e) {
                System.err.println(e);
            } finally {
                scanner = new Scanner(System.in);
            }
        }
    }
}
