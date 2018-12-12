package com.alpha;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class TrainStop {
    private String stationName;
    private LocalDateTime arrivalTime;
    private LocalDateTime departureTime;

    public TrainStop(String stationName, LocalDateTime arrivalTime, LocalDateTime departureTime) {
        this.stationName = stationName;
        this.arrivalTime = arrivalTime;
        this.departureTime = departureTime;
    }

    public String getStationName() {
        return stationName;
    }

    public LocalDateTime getArrivalTime() {
        return arrivalTime;
    }

    public LocalDateTime getDepartureTime() {
        return departureTime;
    }

    static TrainStop findTrainStopByName(List<TrainStop> trainStops, String stopName) {
        for (TrainStop trainStop : trainStops) {
            if (trainStop.getStationName().equals(stopName)) {
                return trainStop;
            }
        }
        return null;
    }

    static List<TrainStop> subListStop(List<TrainStop> trains, String from, String to) {
        TrainStop fromTrainStop = findTrainStopByName(trains, from);
        TrainStop toTrainStop = findTrainStopByName(trains, to);
        if (fromTrainStop == null || toTrainStop == null) {
            throw new RuntimeException("There\'s no  sublist of stops from " + from + " to " + to);
        }
        return trains.subList(trains.indexOf(fromTrainStop), trains.indexOf(toTrainStop) + 1);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TrainStop)) return false;
        TrainStop trainStop = (TrainStop) o;
        return getStationName().equals(trainStop.getStationName()) &&
                getArrivalTime().equals(trainStop.getArrivalTime()) &&
                getDepartureTime().equals(trainStop.getDepartureTime());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getStationName(), getArrivalTime(), getDepartureTime());
    }

    @Override
    public String toString() {
        return "TrainStop{" +
                "stationName='" + stationName + '\'' +
                ", arrivalTime=" + arrivalTime +
                ", departureTime=" + departureTime +
                '}';
    }
}
