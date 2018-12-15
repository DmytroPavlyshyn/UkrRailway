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
    private List<TrainStop> personalRoute;


    public Ticket(LocalDateTime date, String firstName, String lastName, int trainRouteId, int carriageId, int idPlace, List<TrainStop> personalRoute) {
        this.date = date;
        this.firstName = firstName;
        this.lastName = lastName;
        this.trainRouteId = trainRouteId;
        this.carriageId = carriageId;
        this.idPlace = idPlace;
        this.personalRoute = personalRoute;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getTrainRouteId() {
        return trainRouteId;
    }

    public void setTrainRouteId(int trainRouteId) {
        this.trainRouteId = trainRouteId;
    }

    public int getCarriageId() {
        return carriageId;
    }

    public void setCarriageId(int carriageId) {
        this.carriageId = carriageId;
    }

    public int getIdPlace() {
        return idPlace;
    }

    public void setIdPlace(int idPlace) {
        this.idPlace = idPlace;
    }

    public List<TrainStop> getPersonalRoute() {
        return personalRoute;
    }

    public void setPersonalRoute(List<TrainStop> personalRoute) {
        this.personalRoute = personalRoute;
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
                ", \npersonalRoute=" + personalRoute +
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
                Objects.equals(getPersonalRoute(), ticket.getPersonalRoute());
    }

    @Override
    public int hashCode() {
        return Objects.hash(object, getDate(), getFirstName(), getLastName(), getTrainRouteId(), getCarriageId(), getIdPlace(), getPersonalRoute());
    }
}
