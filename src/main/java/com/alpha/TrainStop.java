package com.alpha;

import java.time.LocalDateTime;
import java.util.Date;

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

    @Override
    public String toString() {
        return "TrainStop{" +
                "stationName='" + stationName + '\'' +
                ", arrivalTime=" + arrivalTime +
                ", departureTime=" + departureTime +
                '}';
    }
}
