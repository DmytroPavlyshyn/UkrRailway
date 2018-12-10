package com.alpha;

import java.util.ArrayList;
import java.util.List;

public class Carriage {
    private int id;
    private int capacity;
    private List<Place> places;

    public Carriage(int id,int capacity) {
        this.capacity = capacity;
        places = new ArrayList<>();

        for (int i = 1; i <= capacity; i++) {
            places.add(new Place(i));
        }
    }

    public int getId() {
        return id;
    }

    public int getCapacity() {
        return capacity;
    }

    public List<Place> getPlaces() {
        return places;
    }

    @Override
    public String toString() {
        return "Carriage{" +
                "id=" + id +
                ", capacity=" + capacity +
                ", places=" + places +
                "}\n";
    }
}

