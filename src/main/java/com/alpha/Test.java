package com.alpha;

import java.time.LocalDateTime;
import java.util.Scanner;

public class Test {             ////якщо тут запускати то тільки дату
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        UkrRailway ukrRailway = new UkrRailway();
        boolean isEmpty;
        while (true) {
            try {
                ukrRailway.buyTicket("Rivne", "Kyiv", LocalDateTime.of(2018, 12, 6, 5, 0), "12", "12");
                ukrRailway.buyTicket("Lviv", "Brody", LocalDateTime.of(2018, 12, 6, 1, 0), "12", "12");
                ukrRailway.buyTicket("Brody", "Rivne", LocalDateTime.of(2018, 12, 6, 3, 0), "12", "12");
                ukrRailway.buyTicket("Lviv", "Rivne", LocalDateTime.of(2018, 12, 6, 1, 0), "12", "12");
                ukrRailway.buyTicket("Rivne", "Kyiv", LocalDateTime.of(2018, 12, 6, 5, 0), "12", "12");
                ukrRailway.buyTicket("Rivne", "Kyiv", LocalDateTime.of(2018, 12, 6, 5, 0), "12", "12");
                ukrRailway.buyTicket("Lviv", "Rivne", LocalDateTime.of(2018, 12, 6, 1, 0), "12", "12");
                ukrRailway.buyTicket("Lviv", "Kyiv", LocalDateTime.of(2018, 12, 6, 1, 0), "12", "12");
                ukrRailway.buyTicket("Brody", "Kyiv", LocalDateTime.of(2018, 12, 6, 3, 0), "12", "12");
                ukrRailway.buyTicket("Lviv", "Brody", LocalDateTime.of(2018, 12, 6, 1, 0), "12", "12");
                scanner.nextLine();
            } catch (RuntimeException e) {
                System.err.println(e);
                break;

            }
        }
    }
}