package com.fitness.controllers;

import com.fitness.entities.UserCoach;
import com.fitness.services.UserCoachService;
import com.fitness.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/user-coach")
public class UserCoachController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserCoachService userCoachService;

    @RequestMapping("{user_id}")
    public String index(Model model, @PathVariable(name = "user_id") Long user_id) {
        model.addAttribute("title", "Тренеры пользователя " + user_id);
        model.addAttribute("user_id", user_id);

        List<UserCoach> userCoaches = userCoachService.findByUserId(user_id);
        model.addAttribute("user_coaches", userCoaches);

        return "user-coach/index";
    }

    @RequestMapping("{user_id}/create")
    public ModelAndView create(@PathVariable(name = "user_id") Long user_id) {
        ModelAndView mav = new ModelAndView("user-coach/create");

        mav.getModelMap().addAttribute("title", "Добавить тренера пользователю " + user_id);
        mav.getModelMap().addAttribute("user_id", user_id);

        mav.addObject("user_coach", new UserCoach(userService.findUserById(user_id)));

        return mav;
    }

    @RequestMapping(value = "{user_id}/save", method = RequestMethod.POST)
    public String save(@ModelAttribute("user_coach") UserCoach userCoach, @PathVariable(name = "user_id") Long user_id) {
        userCoachService.save(userCoach);

        return "redirect:/user-coach/" + user_id;
    }

    @RequestMapping("{user_id}/delete/{user_coach_id}")
    public String delete(@PathVariable(name = "user_id") Long user_id, @PathVariable(name = "user_coach_id") Long user_coach_id) {
        userCoachService.del(user_coach_id);

        return "redirect:/user-coach/" + user_id;
    }

}
