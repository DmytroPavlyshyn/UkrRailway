package com.alpha;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UkrRailway {
    List<TrainRoute> trainRoutes = new ArrayList<>();
    {
        trainRoutes.add(new TrainRoute(
                new Train(
                        new ArrayList<Carriage>(){
                            {
                                add(new Carriage(4));
                        }}
                ),LocalDateTime.now().minusMinutes(14),new ArrayList<TrainStop>(){
                    {add(new TrainStop("Lviv", LocalDateTime.now(),LocalDateTime.now().plusMinutes(15)));}
                }));
    }

    public static void main(String[] args) {
        UkrRailway ukrRailway = new UkrRailway();
    }
}
