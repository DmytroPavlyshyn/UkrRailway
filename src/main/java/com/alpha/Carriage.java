package com.alpha;



public class Carriage {
    private int id;
    private CarriageType carriageType;
    private int capacity;


    public Carriage(int id, CarriageType carriageType, int capacity) {
        this.id = id;
        this.carriageType = carriageType;
        this.capacity = capacity;
    }

    public int getId() {
        return id;
    }

    public CarriageType getCarriageType() {
        return carriageType;
    }

    public int getCapacity() {
        return capacity;
    }

    @Override
    public String toString() {
        return "Carriage: " +this.id + "type: " + this.carriageType;
    }
}

