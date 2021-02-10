package com.kyurao.floorlayout.service.rules;

import com.kyurao.floorlayout.domain.Point;

import java.util.List;

public class AtLeastFourCornersRule implements Rule {
    @Override
    public boolean validate(List<Point> points) {
        return points.size() >= 4;
    }

    @Override
    public String getMessage() {
        return "Room must have at least four corners";
    }
}
