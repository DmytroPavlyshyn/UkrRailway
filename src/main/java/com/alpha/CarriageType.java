package com.alpha;

public enum CarriageType {
    SV(4), COMPARATMENT(45), COUCHETTE(34);
    private int capapcity;
    CarriageType(int capapcity) {
        this.capapcity = capapcity;
    }

    public static void main(String[] args) {
        CarriageType.SV.name();
    }
}
