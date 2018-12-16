package com.alpha;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Array;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class TrainStopTest {
    static List<TrainStop> trainStops;
    TrainStop trainStop;

    @Before
    public void setUp() {

        trainStop = new TrainStop("Lviv", LocalDateTime.now(), LocalDateTime.now().plusMinutes(15));

        trainStops = new ArrayList<>();
        trainStops.add(new TrainStop("Lviv", LocalDateTime.now(), LocalDateTime.now().plusMinutes(15)));
        trainStops.add(new TrainStop("Brody", LocalDateTime.now(), LocalDateTime.now().plusMinutes(15)));
        trainStops.add(new TrainStop("Rivne", LocalDateTime.now(), LocalDateTime.now().plusMinutes(15)));
        trainStops.add(new TrainStop("Kyiv", LocalDateTime.now(), LocalDateTime.now().plusMinutes(15)));

    }

    @Test
    public void testGetStationName() {
        assertEquals("Lviv", trainStop.getStationName());
    }

    @Test
    public void testFindTrainStopByName() {
        assertEquals(TrainStop.findTrainStopByName(trainStops, "Brody").getStationName(), "Brody");
    }

    @Test
    public void testSubListOfStops() {
        Assert.assertTrue(TrainStop.subListOfStops(trainStops, "Brody", "Rivne")
                .containsAll(Arrays.asList(trainStops.get(1), trainStops.get(2))));
    }

}
