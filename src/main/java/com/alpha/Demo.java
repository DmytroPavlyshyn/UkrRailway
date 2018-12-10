package com.alpha;

import java.time.LocalDateTime;
import java.util.*;
public class Demo {
    static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        UkrRailway ukrRailway = new UkrRailway();
        boolean isEmpty;
       // System.out.println(" The fastest way" + ukrRailway.findShortestRoute("Lviv", "Rivne", LocalDateTime.of(2018, 12, 6, 8, 38)));
        while (true) {
            System.out.println("Enter month: ");
            Integer month = scanner.nextInt();
            System.out.println("Enter day");
            Integer day = scanner.nextInt();
            scanner.nextLine();
            System.out.println("Enter city of departure: ");
            String from = scanner.nextLine();
            System.out.println("Enter city of arrival: ");
            String to = scanner.nextLine();
            System.out.println("Enter first name: ");
            String firstName = scanner.nextLine();
            System.out.println("Enter last name: ");
            String lastName = scanner.nextLine();
            ukrRailway.buyTicket(from, to, LocalDateTime.of(2018,month , day, 8, 38), firstName, lastName);
        }
    }
}
