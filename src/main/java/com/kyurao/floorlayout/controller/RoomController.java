package com.kyurao.floorlayout.controller;

import com.kyurao.floorlayout.domain.Point;
import com.kyurao.floorlayout.domain.Room;
import com.kyurao.floorlayout.dto.RoomDto;
import com.kyurao.floorlayout.service.RoomService;
import com.kyurao.floorlayout.service.ValidatorService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

import static com.kyurao.floorlayout.constant.ViewConstants.TITLE;
import static com.kyurao.floorlayout.constant.ViewConstants.TOP_MENU_ELEMENT;

@Controller
@RequestMapping("/room")
public class RoomController {

    private final ValidatorService validatorService;
    private final RoomService roomService;

    public RoomController(ValidatorService validatorService,
                          RoomService roomService) {
        this.validatorService = validatorService;
        this.roomService = roomService;
    }

    @GetMapping("add")
    public ModelAndView getViewForAddRoomPage() {
        return new ModelAndView("add-room")
                .addObject(TITLE, "Create room")
                .addObject(TOP_MENU_ELEMENT, "createRoom");
    }

    @GetMapping("all")
    public ModelAndView getViewForAllRoomsPage() {
        return new ModelAndView("all-rooms")
                .addObject(TITLE, "Rooms")
                .addObject(TOP_MENU_ELEMENT, "viewRooms");
    }

    @PostMapping("create")
    @ResponseBody
    public void create(@RequestBody RoomDto req) {
        List<Point> points = validatorService.getValidatedCorners(req.getRoom());
        roomService.saveRoom(new Room(points));
    }

    @PostMapping("validateRoom")
    @ResponseBody
    public RoomDto validateRoom(@RequestBody RoomDto dto) {
        validatorService.getValidatedCorners(dto.getRoom());
        return dto;
    }
}
