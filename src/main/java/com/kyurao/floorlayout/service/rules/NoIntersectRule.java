package com.kyurao.floorlayout.service.rules;

import com.kyurao.floorlayout.domain.Point;

import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

public class NoIntersectRule implements Rule{

    @Override
    public boolean validate(List<Point> points) {
        List<Line2D.Double> lines = new ArrayList<>();

        for (int i = 1; i < points.size() - 1; i++) {
            double x1 = points.get(i - 1).getX().doubleValue();
            double y1 = points.get(i - 1).getY().doubleValue();
            double x2 = points.get(i).getX().doubleValue();
            double y2 = points.get(i).getY().doubleValue();

            lines.add(new Line2D.Double(x1, y1, x2, y2));
        }
        double x1 = points.get(0).getX().doubleValue();
        double y1 = points.get(0).getY().doubleValue();
        double x2 = points.get(points.size() - 1).getX().doubleValue();
        double y2 = points.get(points.size() - 1).getY().doubleValue();
        lines.add(new Line2D.Double(x1, y1, x2, y2));

        for (int i = 0; i < lines.size(); i++) {
            for (int j = i + 1; j < lines.size(); j++) {
                Line2D.Double l1 = lines.get(i);
                Line2D.Double l2 = lines.get(j);
                if (l1.intersectsLine(l2) && !haveShareCornerAndNotPartOfEachOther(l1, l2)) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean haveShareCornerAndNotPartOfEachOther(Line2D l1, Line2D l2) {
        Point2D p1 = l1.getP1();
        Point2D p2 = l1.getP2();
        Point2D p3 = l2.getP1();
        Point2D p4 = l2.getP2();

        if ((l1.contains(p3) && l1.contains(p4)) || (l2.contains(p1) && l2.contains(p2))) {
            return false;
        } else if (p1.equals(p3) && p2.equals(p4)) {
            return true;
        }
        else {
            return p2.equals(p3) || p2.equals(p4);
        }
    }

    @Override
    public String getMessage() {
        return "The Walls cannot intersect";
    }
}
