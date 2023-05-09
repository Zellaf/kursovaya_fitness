package com.fitness.controllers;

import com.fitness.entities.User;
import com.fitness.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/registration")
public class RegistrationController {

    @Autowired
    private UserService userService;

    @GetMapping({"", "/"})
    public String registration(Model model) {
        model.addAttribute("user", new User());
        return "registr";
    }

    @PostMapping({"", "/"})
    public String addUser(@ModelAttribute("user") @Valid User userForm, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "registr";
        }

        if (!userForm.getPassword().equals(userForm.getPasswordConfirm())) {
            model.addAttribute("error", "Пароли не совпадают");
            return "registr";
        }

        if (!userService.saveUser(userForm)) {
            model.addAttribute("error", "Пользователь с таким именем уже существует");
            return "registr";
        }

        return "redirect:/login";
    }
    
}
