package com.alpha;

import java.util.List;

public class Train {
    private int id = count++;
    private static int count = 1;
    private List<Carriage> carriages;
    public Train(List<Carriage> carriages) {
        this.carriages = carriages;
    }
    public int getGeneralCapacity(){
        int capacity = 0;
        for(Carriage carriage: carriages){
            capacity+=carriage.getCapacity();
        }
        return capacity;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Train{" +
                "id=" + id +
                ", carriages=" + carriages +
                '}';
    }
}
