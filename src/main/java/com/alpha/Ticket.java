package com.alpha;

import java.time.LocalDateTime;
import java.util.Objects;

public class Ticket {
    private LocalDateTime date;
    private String firstName;
    private String lastName;
    private int trainId;
    private int carriageId;
    private int idPlace;
    private TrainStop start;
    private TrainStop end;

    public TrainStop getStart() {
        return start;
    }

    public TrainStop getEnd() {
        return end;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Ticket)) return false;
        Ticket ticket = (Ticket) o;
        return trainId == ticket.trainId &&
                carriageId == ticket.carriageId &&
                idPlace == ticket.idPlace &&
                Objects.equals(date, ticket.date) &&
                Objects.equals(firstName, ticket.firstName) &&
                Objects.equals(lastName, ticket.lastName) &&
                Objects.equals(start, ticket.start) &&
                Objects.equals(end, ticket.end);
    }

    @Override
    public int hashCode() {
        return Objects.hash(date, firstName, lastName, trainId, carriageId, idPlace, start, end);
    }
}
