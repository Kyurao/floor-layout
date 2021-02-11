package com.kyurao.floorlayout.controller;

import com.kyurao.floorlayout.domain.Point;
import com.kyurao.floorlayout.domain.Room;
import com.kyurao.floorlayout.dto.RoomDto;
import com.kyurao.floorlayout.service.RoomService;
import com.kyurao.floorlayout.service.ValidatorService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/")
public class RoomController {

    private final ValidatorService validatorService;
    private final RoomService roomService;

    public RoomController(ValidatorService validatorService,
                          RoomService roomService) {
        this.validatorService = validatorService;
        this.roomService = roomService;
    }

    @PostMapping("room/create")
    public void create(@RequestBody RoomDto req) {
        List<Point> points = validatorService.getValidatedCorners(req.getRoom());
        roomService.saveRoom(new Room(points));
    }

    @PostMapping("validateRoom")
    public RoomDto validateRoom(@RequestBody RoomDto dto) {
        validatorService.getValidatedCorners(dto.getRoom());
        return dto;
    }
}
