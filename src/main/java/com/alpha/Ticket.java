package com.alpha;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

public class Ticket {
    Object object;

    private LocalDateTime date;
    private String firstName;
    private String lastName;
    private int trainRouteId;
    private int carriageId;
    private int idPlace;
    private List<TrainStop> personalStops;


    public Ticket(LocalDateTime date, String firstName, String lastName, int trainRouteId, int carriageId, int idPlace, List<TrainStop> personalStops) {
        this.date = date;
        this.firstName = firstName;
        this.lastName = lastName;
        this.trainRouteId = trainRouteId;
        this.carriageId = carriageId;
        this.idPlace = idPlace;
        this.personalStops = personalStops;
    }

    public LocalDateTime getDate() {
        return date;
    }


    public String getFirstName() {
        return firstName;
    }


    public String getLastName() {
        return lastName;
    }


    public int getTrainRouteId() {
        return trainRouteId;
    }


    public int getCarriageId() {
        return carriageId;
    }


    public int getIdPlace() {
        return idPlace;
    }


    public List<TrainStop> getPersonalStops() {
        return personalStops;
    }


    @Override
    public String toString() {
        return "Ticket{" +
                "date=" + date +
                ", \nfirstName='" + firstName + '\'' +
                ", \nlastName='" + lastName + '\'' +
                ", \ntrainRouteId=" + trainRouteId +
                ", \ncarriageId=" + carriageId +
                ", \nidPlace=" + idPlace +
                ", \npersonalStops=" + personalStops +
                "}\n";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Ticket)) return false;
        Ticket ticket = (Ticket) o;
        return getTrainRouteId() == ticket.getTrainRouteId() &&
                getCarriageId() == ticket.getCarriageId() &&
                getIdPlace() == ticket.getIdPlace() &&
                Objects.equals(object, ticket.object) &&
                Objects.equals(getDate(), ticket.getDate()) &&
                Objects.equals(getFirstName(), ticket.getFirstName()) &&
                Objects.equals(getLastName(), ticket.getLastName()) &&
                Objects.equals(getPersonalStops(), ticket.getPersonalStops());
    }

    @Override
    public int hashCode() {
        return Objects.hash(object, getDate(), getFirstName(), getLastName(), getTrainRouteId(), getCarriageId(), getIdPlace(), getPersonalStops());
    }
}
