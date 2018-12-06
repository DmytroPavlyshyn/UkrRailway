package com.alpha;

import java.util.Date;

public class TrainStop {
    private String stationName;
    private Date arrivalTime;
    private Date departureTime;

    public TrainStop(String stationName, Date arrivalTime, Date departureTime) {
        this.stationName = stationName;
        this.arrivalTime = arrivalTime;
        this.departureTime = departureTime;
    }
}
