package com.alpha;

public enum TrainStopNames {
    Lviv(0001), Brody(0002), Rivne(0003), Kyiv(0004);

    public final int trainStopId;

    TrainStopNames(int trainStopId) {
        this.trainStopId = trainStopId;
    }
}
