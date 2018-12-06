package com.alpha;

import java.time.LocalDateTime;
import java.util.*;

public class TrainRoute {
    private Train train;
    private LocalDateTime data;
    private List<TrainStop> trainStops;
    private List<Ticket> tickets;

    public TrainRoute(Train train, LocalDateTime data, ArrayList<TrainStop> trainStops) {
        this.train = train;
        this.data = data;
        this.trainStops = trainStops;
        tickets = new ArrayList<Ticket>();
    }



}
