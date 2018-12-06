package com.alpha;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

public class Ticket2 {
    private LocalDateTime date;
    private String firstName;
    private String lastName;
    private int trainId;
    private int carriageId;
    private int idPlace;
    private List<TrainStop> personalRoute;

    public Ticket2(LocalDateTime date, String firstName, String lastName, int trainId, int carriageId, int idPlace, List<TrainStop> personalRoute) {
        this.date = date;
        this.firstName = firstName;
        this.lastName = lastName;
        this.trainId = trainId;
        this.carriageId = carriageId;
        this.idPlace = idPlace;
        this.personalRoute = personalRoute;
    }

    public List<TrainStop> getPersonalRoute() {
        return personalRoute;
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
                '}';
    }
}
