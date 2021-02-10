package com.kyurao.floorlayout.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import static com.kyurao.floorlayout.constant.ViewConstants.TITLE;
import static com.kyurao.floorlayout.constant.ViewConstants.TOP_MENU_ELEMENT;


@Controller
@RequestMapping("/")
public class MainController {

    @GetMapping
    public ModelAndView greeting() {
        return new ModelAndView("home")
                .addObject(TITLE, "Main page")
                .addObject(TOP_MENU_ELEMENT, "home");
    }
}