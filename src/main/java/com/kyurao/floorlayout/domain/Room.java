package com.kyurao.floorlayout.domain;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "ROOM")
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ElementCollection
    @CollectionTable(name="ROOM_CORNERS", joinColumns=@JoinColumn(name="ROOM_ID"))
    private List<Point> points;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Point> getPoints() {
        return points;
    }

    public void setPoints(List<Point> points) {
        this.points = points;
    }
}
