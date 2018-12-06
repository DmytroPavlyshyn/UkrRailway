package com.alpha;

import java.time.LocalDateTime;

public class TrainStop {
    private TrainStopNames trainStopName;
    private LocalDateTime arrivalTime;
    private LocalDateTime departureTime;

    public TrainStop(TrainStopNames trainStopName, LocalDateTime arrivalTime, LocalDateTime departureTime) {
        this.trainStopName = trainStopName;
        this.arrivalTime = arrivalTime;
        this.departureTime = departureTime;
    }
}
