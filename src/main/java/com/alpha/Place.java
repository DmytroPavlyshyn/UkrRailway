package com.alpha;

public class Place {
    private int id = count++;
    private static int count = 1;
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
