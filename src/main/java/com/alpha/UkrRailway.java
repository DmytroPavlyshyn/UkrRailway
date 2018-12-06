package com.alpha;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UkrRailway {
    static List<TrainRoute> trainRoutes = new ArrayList<>();
    static {
        trainRoutes.add(new TrainRoute(
                new Train(new ArrayList<Carriage>(){{add(new Carriage(4));}}),
                LocalDateTime.now(),
                new ArrayList<TrainStop>(){{
                    add(new TrainStop(TrainStopNames.Lviv,
                            null,
                            LocalDateTime.of(2018, 06, 1, 00, 00)));
                    add(new TrainStop(TrainStopNames.Brody,
                            LocalDateTime.of(2018, 06, 1, 00, 00).plusHours(1),
                            LocalDateTime.of(2018, 06, 1, 00, 00).plusHours(1).plusMinutes(3)));
                    add(new TrainStop(TrainStopNames.Rivne,
                            LocalDateTime.of(2018, 06, 1, 00, 00).plusHours(1).plusMinutes(3).plusHours(1),
                            LocalDateTime.of(2018, 06, 1, 00, 00).plusHours(1).plusMinutes(3).plusHours(1).plusMinutes(3)));
                    add(new TrainStop(TrainStopNames.Kyiv,
                            LocalDateTime.of(2018, 06, 1, 00, 00).plusHours(1).plusMinutes(3).plusHours(1).plusMinutes(3).plusHours(1),
                            null));
                    }}
                ));

    }

//    public static void buyTickets(String firstName, String lastName,
//                                  TrainStopNames from, TrainStopNames to,
//                                  LocalDateTime dateTime) {
//
//    }

//    private static boolean isFree(TrainRoute trainRoute,
//                                  TrainStopNames from, TrainStopNames to,
//                                  LocalDateTime dateTime){
//        return true;
//    }

    public static void main(String[] args) {

    }
}
