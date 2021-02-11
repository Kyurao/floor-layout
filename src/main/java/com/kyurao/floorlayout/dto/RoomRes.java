package com.kyurao.floorlayout.dto;

import com.kyurao.floorlayout.domain.Point;

import java.util.List;

public class RoomRes {

    private Long id;

    private Integer quantity;

    private List<Point> corners;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public List<Point> getCorners() {
        return corners;
    }

    public void setCorners(List<Point> corners) {
        this.corners = corners;
    }
}
