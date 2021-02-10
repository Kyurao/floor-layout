package com.kyurao.floorlayout.service.rules;

import com.kyurao.floorlayout.domain.Point;

import java.util.List;

public class RoomFinishedArea implements Rule {
    @Override
    public boolean validate(List<Point> points) {
        Point first = points.get(0);
        Point last = points.get(points.size() - 1);
        return first.getX().equals(last.getX()) || first.getY().equals(last.getY());
    }

    @Override
    public String getMessage() {
        return "Room has no a finite area";
    }
}
