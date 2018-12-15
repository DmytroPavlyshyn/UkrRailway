package com.alpha;

import java.time.LocalDateTime;
import java.util.*;

public class TrainRoute {
    private int id;
    private List<Carriage> carriages;
    private List<TrainStop> trainStops;
    private List<Ticket> tickets = new ArrayList<>();

    public TrainRoute() {
    }

    public TrainRoute(int id, List<Carriage> carriages, List<TrainStop> trainStops) {
        this.id = id;
        this.carriages = carriages;
        this.trainStops = trainStops;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Carriage> getCarriages() {
        return carriages;
    }

    public void setCarriages(List<Carriage> carriages) {
        this.carriages = carriages;
    }

    public void setTrainStops(List<TrainStop> trainStops) {
        this.trainStops = trainStops;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }

    public LocalDateTime getDepatureDate() {
        return trainStops.get(0).getDepartureTime();
    }

    public int getGeneralCapacity() {
        int capacity = 0;
        for (Carriage carriage : carriages) {
            capacity += carriage.getCapacity();
        }
        return capacity;
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TrainRoute)) return false;
        TrainRoute that = (TrainRoute) o;
        return getId() == that.getId() &&
                Objects.equals(getCarriages(), that.getCarriages()) &&
                Objects.equals(getTrainStops(), that.getTrainStops()) &&
                Objects.equals(getTickets(), that.getTickets());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getCarriages(), getTrainStops(), getTickets());
    }

    @Override
    public String toString() {
        return "\nTrainRoute{" +
                "id=" + id +
                ", carriages=" + carriages +
                ", trainStops=" + trainStops +
                ", tickets=" + tickets +
                "}\n";
    }
}

