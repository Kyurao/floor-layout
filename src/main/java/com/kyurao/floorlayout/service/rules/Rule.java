package com.kyurao.floorlayout.service.rules;

import com.kyurao.floorlayout.domain.Point;

import java.util.List;

public interface Rule {
    boolean validate(List<Point> points);

    String getMessage();
}
