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
}
