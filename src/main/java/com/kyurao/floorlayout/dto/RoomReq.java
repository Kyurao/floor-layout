package com.kyurao.floorlayout.dto;


import com.kyurao.floorlayout.domain.Point;

import java.util.ArrayList;
import java.util.List;

public class RoomReq {

    private List<Point> corners = new ArrayList<>();

    public List<Point> getCorners() {
        return corners;
    }

    public void setCorners(List<Point> corners) {
        this.corners = corners;
    }
}
