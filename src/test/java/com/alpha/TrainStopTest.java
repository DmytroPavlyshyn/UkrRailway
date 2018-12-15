package com.alpha;

import org.junit.*;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.*;


import static org.junit.Assert.*;

public class TrainStopTest {
    TrainStop trainStop;

    @Before
    public void setUp() {
        trainStop = new TrainStop("Lviv", LocalDateTime.now(), LocalDateTime.now().plusMinutes(15));
    }

    @Test
    public void testGetStationName() {
        assertEquals("Lviv", trainStop.getStationName());
    }

    @Test
    public void testFindTrainStopByName() {
        List<TrainStop> trainStops = new ArrayList<>(Arrays.asList(trainStop));
        assertEquals(TrainStop.findTrainStopByName(trainStops, "Lviv").getStationName(), "Lviv");
    }

}
