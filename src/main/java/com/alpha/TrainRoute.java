package com.alpha;

import java.util.*;

public class TrainRoute {
    private Train train;
    private Date data;
    private List<TrainStop> trainStops;
    private List<Ticket> tickets;

    public TrainRoute(Train train, Date data, ArrayList<TrainStop> trainStops) {
        this.train = train;
        this.data = data;
        this.trainStops = trainStops;
        tickets = new ArrayList<Ticket>();
    }



}
