package com.alpha.Entity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name="ticket")
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;

    @Column(name="date")
    private LocalDateTime date;

    @Column(name="firstName")
    private String firstName;

    @Column(name="lastName")
    private String lastName;

    @ManyToOne
    @JoinColumn(name = "route_id")
    private Route route;

    @Column(name="carriage_id")
    private int carriageId;

    @Column(name="place_number")
    private int placeNumber;

    @ManyToOne
    @JoinColumn(name = "route_station_start_id")
    private RouteStation start;

    @ManyToOne
    @JoinColumn(name = "route_station_end_id")
    private RouteStation end;

    public Ticket() {
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

    public Route getRoute() {
        return route;
    }

    public void setRoute(Route route) {
        this.route = route;
    }

    public int getCarriageId() {
        return carriageId;
    }

    public void setCarriageId(int carriageId) {
        this.carriageId = carriageId;
    }

    public int getPlaceNumber() {
        return placeNumber;
    }

    public void setPlaceNumber(int placeNumber) {
        this.placeNumber = placeNumber;
    }

    public RouteStation getStart() {
        return start;
    }

    public void setStart(RouteStation start) {
        this.start = start;
    }

    public RouteStation getEnd() {
        return end;
    }

    public void setEnd(RouteStation end) {
        this.end = end;
    }
}
