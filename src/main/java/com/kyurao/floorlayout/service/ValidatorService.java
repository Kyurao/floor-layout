package com.kyurao.floorlayout.service;

import com.kyurao.floorlayout.domain.Point;
import com.kyurao.floorlayout.dto.PointDto;
import com.kyurao.floorlayout.exception.RoomException;
import com.kyurao.floorlayout.service.rules.AtLeastFourCornersRule;
import com.kyurao.floorlayout.service.rules.NoDiagonalRule;
import com.kyurao.floorlayout.service.rules.RoomFinishedArea;
import com.kyurao.floorlayout.service.rules.Rule;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ValidatorService {

    private final List<Rule> rules = new ArrayList<>();

    @PostConstruct
    void init() {
        rules.add(new AtLeastFourCornersRule());
        rules.add(new RoomFinishedArea());
        rules.add(new NoDiagonalRule());
    }

    public List<Point> getValidatedCorners(List<PointDto> corners) {
        List<Point> points = corners
                .stream()
                .map(this::toPoint)
                .collect(Collectors.toList());

        validateWithReason(points);

        return points;
    }

    private Point toPoint(PointDto req) {
        try {
            Integer x = Integer.valueOf(req.getX());
            Integer y = Integer.valueOf(req.getY());
            Point point = new Point();
            point.setX(x);
            point.setY(y);
            return point;
        } catch (NumberFormatException e) {
            throw new RoomException("Coordinates must be integers");
        }
    }

    private void validateWithReason(List<Point> points) {
        for (Rule rule : rules) {
            if (!rule.validate(points)) {
                throw new RoomException(rule.getMessage());
            }
        }
    }
}
