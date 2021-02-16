package com.kyurao.floorlayout.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import static com.kyurao.floorlayout.constant.ViewConstants.ROOM_ID;
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

    @GetMapping("view/add")
    public ModelAndView getViewForAddRoomPage() {
        return new ModelAndView("add-room")
                .addObject(TITLE, "Create room")
                .addObject(TOP_MENU_ELEMENT, "createEditRoom");
    }

    @GetMapping("view/all")
    public ModelAndView getViewForAllRoomsPage() {
        return new ModelAndView("all-rooms")
                .addObject(TITLE, "Rooms")
                .addObject(TOP_MENU_ELEMENT, "viewRooms");
    }

    @GetMapping("view/edit/{id}")
    public ModelAndView getViewForEditRoomPage(@PathVariable Long id) {
        return new ModelAndView("edit-room")
                .addObject(TITLE, "Edit room")
                .addObject(TOP_MENU_ELEMENT, "viewRooms")
                .addObject(ROOM_ID, id);
    }
}