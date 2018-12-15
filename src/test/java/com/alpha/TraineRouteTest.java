package com.alpha;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TraineRouteTest {
    TrainRoute trainRoute;
    List<TrainStop> trainStopList;
    @Before
    public  void setUp(){
        trainRoute = new TrainRoute(1,Arrays.asList(new Carriage(1,CarriageType.SV)),
                new ArrayList<TrainStop>(Arrays.asList(new TrainStop("Lviv", LocalDateTime.now(), LocalDateTime.now().plusMinutes(15)),
                        new TrainStop("Rivne", LocalDateTime.now(), LocalDateTime.now().plusMinutes(15)),
                        new TrainStop("Kyiv", LocalDateTime.now(), LocalDateTime.now().plusMinutes(15)))));
    }
    @Test
    public void testGeneralCapacity(){
        Assert.assertEquals(trainRoute.getGeneralCapacity(),CarriageType.SV.getCapacity());
    }
    @Test
    public void testFineIndexOfTrainStopByName(){
        Assert.assertEquals(trainRoute.findIndexOfTrainStopByName("Rivne"),1);
        Assert.assertEquals(trainRoute.findIndexOfTrainStopByName("123"),-1);
    }
}
