package com.alpha;

import java.time.LocalDateTime;
import java.util.List;


public class Ticket {
    private LocalDateTime date;
    private String firstName;
    private String lastName;
    private int trainId;
    private int carriageId;
    private int idPlace;
    private List<TrainStop> personalRoute;

    public Ticket(LocalDateTime date, String firstName, String lastName, int trainId, int carriageId, int idPlace, List<TrainStop> personalRoute) {
        this.date = date;
        this.firstName = firstName;
        this.lastName = lastName;
        this.trainId = trainId;
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

    public int getTrainId() {
        return trainId;
    }

    public void setTrainId(int trainId) {
        this.trainId = trainId;
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
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", trainId=" + trainId +
                ", carriageId=" + carriageId +
                ", idPlace=" + idPlace +
                ", personalRoute=" + personalRoute +
                "}\n";
    }

}
