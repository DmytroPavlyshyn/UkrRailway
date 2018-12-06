package com.alpha;

import java.util.ArrayList;
import java.util.List;

public class Carriage {         //Вагон
    private int id = count++;
    private static int count = 1;
    private int capacity;
    private List<Place> places;

    public Carriage(int capacity) {
        this.capacity = capacity;
        places = new ArrayList<>();

        for (int i = 0; i < capacity; i++) {
            places.add(new Place());
        }
    }
}

