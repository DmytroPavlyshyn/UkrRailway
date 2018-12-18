package com.alpha;

import java.time.LocalDateTime;
import java.util.Objects;

public class Ticket {

    private LocalDateTime date;
    private String firstName;
    private String lastName;
    private int trainRouteId;
    private int carriageId;
    private int idPlace;
    private TrainStop start;
    private TrainStop end;

    public Ticket(LocalDateTime date, String firstName, String lastName, int trainRouteId, int carriageId, int idPlace, TrainStop start, TrainStop end) {
        this.date = date;
        this.firstName = firstName;
        this.lastName = lastName;
        this.trainRouteId = trainRouteId;
        this.carriageId = carriageId;
        this.idPlace = idPlace;
        this.start = start;
        this.end = end;
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

    public TrainStop getStart() {
        return start;
    }

    public TrainStop getEnd() {
        return end;
    }

    @Override
    public String toString() {
        return "\nTicket{" +
                "\ndate=" + date +
                ",\nfirstName='" + firstName + '\'' +
                ",\nlastName='" + lastName + '\'' +
                ",\ntrainRouteId=" + trainRouteId +
                ",\ncarriageId=" + carriageId +
                ",\nidPlace=" + idPlace +
                ",\nstart=" + start +
                ",\nend=" + end +
                "\n}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Ticket)) return false;
        Ticket ticket = (Ticket) o;
        return getTrainRouteId() == ticket.getTrainRouteId() &&
                getCarriageId() == ticket.getCarriageId() &&
                getIdPlace() == ticket.getIdPlace() &&
                getDate().equals(ticket.getDate()) &&
                getFirstName().equals(ticket.getFirstName()) &&
                getLastName().equals(ticket.getLastName()) &&
                getStart().equals(ticket.getStart()) &&
                getEnd().equals(ticket.getEnd());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getDate(), getFirstName(), getLastName(), getTrainRouteId(), getCarriageId(), getIdPlace(), getStart(), getEnd());
    }
}
