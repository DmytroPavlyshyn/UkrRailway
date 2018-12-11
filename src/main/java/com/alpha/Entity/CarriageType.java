package com.alpha.Entity;

public enum CarriageType {
    SV(20), COMPARATMENT(36), COUCHETTE(54);

    private int capasity;

    CarriageType(int capasity) {
        this.capasity = capasity;
    }

    public int getCapasity() {
        return capasity;
    }
}
