package com.alpha;

import java.text.SimpleDateFormat;
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
                ),new Date(2018,1,2),new ArrayList<TrainStop>(){
                  //  {add(new TrainStop("Lviv",new Date(2018,12,6)))}
                }));
    }
}
