package com.kyurao.floorlayout.controller;

import com.kyurao.floorlayout.domain.Point;
import com.kyurao.floorlayout.domain.Room;
import com.kyurao.floorlayout.dto.RoomDto;
import com.kyurao.floorlayout.dto.RoomRes;
import com.kyurao.floorlayout.service.RoomService;
import com.kyurao.floorlayout.service.ValidatorService;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("validateRoom")
    public RoomDto validateRoom(@RequestBody RoomDto dto) {
        validatorService.getValidatedCorners(dto.getRoom());
        return dto;
    }

    @PostMapping("room/create")
    public void create(@RequestBody RoomDto req) {
        List<Point> points = validatorService.getValidatedCorners(req.getRoom());
        roomService.saveRoom(new Room(points));
    }

    @GetMapping("room/{id}")
    public RoomRes getRoom(@PathVariable Long id) {
        return roomService.getRoom(id);
    }

    @GetMapping("room/all")
    public List<RoomRes> getAllRooms() {
        return roomService.getAllRooms();
    }

    @PutMapping("room/update/{id}")
    public void updateRoom(@PathVariable Long id,
                           @RequestBody RoomDto req) {
        List<Point> points = validatorService.getValidatedCorners(req.getRoom());
        roomService.editRoom(id, points);
    }

    @DeleteMapping("room/delete/{id}")
    public void deleteRoom(@PathVariable Long id) {
        roomService.removeRoom(id);
    }
}
