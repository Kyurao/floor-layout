package com.kyurao.floorlayout.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import static com.kyurao.floorlayout.constant.ViewConstants.TITLE;
import static com.kyurao.floorlayout.constant.ViewConstants.TOP_MENU_ELEMENT;


@Controller
@RequestMapping("/")
public class ViewController {

    @GetMapping
    public ModelAndView getViewForHomePage() {
        return new ModelAndView("home")
                .addObject(TITLE, "Main page")
                .addObject(TOP_MENU_ELEMENT, "home");
    }

    @GetMapping("room/add")
    public ModelAndView getViewForAddRoomPage() {
        return new ModelAndView("add-room")
                .addObject(TITLE, "Create room")
                .addObject(TOP_MENU_ELEMENT, "createRoom");
    }

    @GetMapping("room/all")
    public ModelAndView getViewForAllRoomsPage() {
        return new ModelAndView("all-rooms")
                .addObject(TITLE, "Rooms")
                .addObject(TOP_MENU_ELEMENT, "viewRooms");
    }
}