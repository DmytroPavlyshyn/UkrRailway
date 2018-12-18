package com.alpha;

public enum CarriageType {
    SV(4), COMPARATMENT(45), COUCHETTE(34);
    private int capacity;
    CarriageType(int capapcity) {
        this.capacity = capapcity;
    }

    public int getCapacity() {
        return capacity;
    }


}
