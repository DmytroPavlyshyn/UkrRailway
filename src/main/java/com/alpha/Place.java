package com.alpha;

public class Place {
    private int id;

    public Place(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Place{" +
                "id=" + id +
                '}';
    }
}
