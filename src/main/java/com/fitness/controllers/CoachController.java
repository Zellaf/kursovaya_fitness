package com.fitness.controllers;

import com.fitness.entities.Coach;
import com.fitness.services.CoachService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
@RequestMapping("/coach")
public class CoachController {

    @Autowired
    private CoachService coachService;

    @RequestMapping({"", "/"})
    public String index(Model model) {
        model.addAttribute("title", "Тренеры");

        List<Coach> list = coachService.findAll();

        model.addAttribute("coach_list", list);

        return "coach/index";
    }

    @RequestMapping(value = "/delete/{coach_id}")
    public String delete(@PathVariable(name = "coach_id") Long coach_id) {
        coachService.del(coach_id);

        return "redirect:/coach";
    }

}
