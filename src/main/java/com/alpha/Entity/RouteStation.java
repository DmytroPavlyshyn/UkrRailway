package com.alpha.Entity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name="route_station")
public class RouteStation {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name="id")
    private int id;

    @ManyToOne
    @JoinColumn(name="station_id")
    private Station station ;

    @ManyToOne
    @JoinColumn(name = "route_id")
    private Route route;

    @Column(name="ordinance")
    private int ordinance;

    @Column(name="arrival_time")
    private LocalDateTime arrivalTime;

    @Column(name="departure_time")
    private LocalDateTime departureTime;

    public RouteStation() {

    }

    public RouteStation(Station station, Route route, LocalDateTime arrivalTime, LocalDateTime departureTime) {
        this.station = station;
        this.route = route;
        this.arrivalTime = arrivalTime;
        this.departureTime = departureTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Station getStation() {
        return station;
    }

    public void setStation(Station station) {
        this.station = station;
    }

    public Route getRoute() {
        return route;
    }

    public void setRoute(Route route) {
        this.route = route;
    }

    public LocalDateTime getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(LocalDateTime arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public LocalDateTime getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(LocalDateTime departureTime) {
        this.departureTime = departureTime;
    }
}
