package com.alpha;

import java.time.LocalDateTime;
import java.util.*;

public class TrainRoute {
    private Train train;
    private List<TrainStop> trainStops;
    private List<Ticket> tickets;

    public TrainRoute(Train train, ArrayList<TrainStop> trainStops) {
        this.train = train;
        this.trainStops = trainStops;
        tickets = new ArrayList<Ticket>();
    }

    public Train getTrain() {
        return train;
    }

    public LocalDateTime getDepatureDate() {
        return trainStops.get(0).getDepartureTime();
    }

    public List<TrainStop> getTrainStops() {
        return trainStops;
    }

    public List<Ticket> getTickets() {
        return tickets;
    }
    public int findIndexOfTrainStopByName(String name){
        for(TrainStop trainStop:trainStops){
            if(trainStop.getStationName().equals(name)){
                return trainStops.indexOf(trainStop);
            }
        }
        return -1;
    }

    @Override
    public String toString() {
        return "TrainRoute{" +
                "train: \n" + train +
                ", trainStops: \n" + trainStops +
                ", tickets: \n" + tickets +
                "}\n";
    }
}

