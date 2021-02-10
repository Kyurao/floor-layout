package com.kyurao.floorlayout.service.rules;

import com.kyurao.floorlayout.domain.Point;

import java.util.List;

public class NoDiagonalRule implements Rule {
    @Override
    public boolean validate(List<Point> points) {
        for (int i = 0; i < points.size() - 1; i++) {
            Point current = points.get(i);
            Point next = points.get(i + 1);
            if (!current.getX().equals(next.getX()) && !current.getY().equals(next.getY())) {
                return false;
            }
        }

        Point first = points.get(0);
        Point last = points.get(points.size() - 1);
        return first.getX().equals(last.getX()) || first.getY().equals(last.getY());
    }

    @Override
    public String getMessage() {
        return "The Room cannot has a diagonal wall";
    }
}
