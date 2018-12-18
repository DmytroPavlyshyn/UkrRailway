package com.alpha;


import java.util.Objects;

public class Carriage {
    private int id;
    private CarriageType carriageType;

    public Carriage(int id, CarriageType carriageType) {
        this.id = id;
        this.carriageType = carriageType;
    }

    public int getId() {
        return id;
    }

    public CarriageType getCarriageType() {
        return carriageType;
    }

    public int getCapacity() {
        return carriageType.getCapacity();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Carriage)) return false;
        Carriage carriage = (Carriage) o;
        return getId() == carriage.getId() &&
                getCarriageType() == carriage.getCarriageType();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getCarriageType());
    }

    @Override
    public String toString() {
        return "Carriage: " + this.id + "  type: " + this.carriageType;
    }
}

