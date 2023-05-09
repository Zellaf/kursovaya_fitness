package com.fitness.controllers;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;

@Controller
public class ErrorControllerHandler implements ErrorController {

    @RequestMapping("/error")
    public String renderErrorPage(Model model) {
        model.addAttribute("title", "Ошибка");

        model.addAttribute("message", "Такой страницы не существует или у вас нет доступа к ней");

        return "error";
    }
}
