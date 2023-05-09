package com.fitness.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AboutMeController {

    @RequestMapping({"", "/"})
    public String index(Model model) {
        model.addAttribute("title", "Главная");

        return "index";
    }

}
