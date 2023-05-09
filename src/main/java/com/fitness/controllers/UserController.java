package com.fitness.controllers;

import com.fitness.entities.Coach;
import com.fitness.entities.Role;
import com.fitness.entities.User;
import com.fitness.services.CoachService;
import com.fitness.services.RoleService;
import com.fitness.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private CoachService coachService;

    @RequestMapping({"", "/"})
    public String index(Model model, @Param("search_keyword") String search_keyword) {
        model.addAttribute("title", "Пользователи");
        model.addAttribute("search_keyword", search_keyword);

        List<User> list = null;

        if (search_keyword != null)
            list = userService.allUsers(search_keyword);
        else
            list = userService.allUsers();

        model.addAttribute("user_list", list);

        return "user/index";
    }

    @RequestMapping("/edit/{id}")
    public ModelAndView edit(@PathVariable(name = "id") Long id) {
        ModelAndView mav = new ModelAndView("user/edit");

        mav.getModelMap().addAttribute("title", "Редактирование пользователя " + id);

        User user = userService.findUserById(id);
        mav.addObject("user", user);

        roleService.generateDefaultIfNotExitst();
        List<Role> roles = roleService.findAll();
        mav.getModelMap().addAttribute("roles_list", roles);

        return mav;
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(@ModelAttribute("user") @Valid User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "redirect:/user";

        List<Role> roles = user.getRoles();
        for (Role role : roles)
            if (!roleService.isRoleExist(role.getName()))
                return "redirect:/user";

        userService.updateUser(user);

        return "redirect:/user";
    }

    @RequestMapping(value = "/make-coach/{user_id}")
    public String makeCoach(@PathVariable(name = "user_id") Long user_id) {
        coachService.save(new Coach(userService.findUserById(user_id)));

        return "redirect:/user";
    }

}
