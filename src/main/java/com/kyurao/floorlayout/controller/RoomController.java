package com.kyurao.floorlayout.controller;

import com.kyurao.floorlayout.domain.Point;
import com.kyurao.floorlayout.dto.RoomDto;
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

    public RoomController(ValidatorService validatorService) {
        this.validatorService = validatorService;
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
        System.out.println(req.getRoom());
        List<Point> points = validatorService.getValidatedCorners(req.getRoom());
        System.out.println(points);
        //roomService.create(points);
    }

    @PostMapping("validateRoom")
    @ResponseBody
    public RoomDto validateRoom(@RequestBody RoomDto dto) {
        validatorService.getValidatedCorners(dto.getRoom());
        return dto;
    }
}
