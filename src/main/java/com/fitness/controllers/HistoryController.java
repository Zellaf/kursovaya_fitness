package com.fitness.controllers;

import com.fitness.entities.History;
import com.fitness.services.HistoryService;
import com.fitness.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/history")
public class HistoryController {

    @Autowired
    private HistoryService historyService;

    @Autowired
    private UserService userService;

    @RequestMapping("{user_id}")
    public String index(Model model, @PathVariable(name = "user_id") Long user_id, @Param("search_keyword") String search_keyword) {
        model.addAttribute("title", "История пользователя " + user_id);
        model.addAttribute("user_id", user_id);
        model.addAttribute("search_keyword", search_keyword);

        List<History> historyList = null;

        if (search_keyword != null)
            historyList = historyService.findByUserId(user_id, search_keyword);
        else
            historyList = historyService.findByUserId(user_id);

        model.addAttribute("history_list", historyList);

        return "history/index";
    }

    @RequestMapping("{user_id}/create")
    public ModelAndView create(@PathVariable(name = "user_id") Long user_id) {
        ModelAndView mav = new ModelAndView("history/create");

        mav.getModelMap().addAttribute("title", "Добавить запись в историю пользователя " + user_id);
        mav.getModelMap().addAttribute("user_id", user_id);

        mav.addObject("history", new History(userService.findUserById(user_id)));

        return mav;
    }

    @RequestMapping("{user_id}/edit/{history_id}")
    public ModelAndView edit(@PathVariable(name = "user_id") Long user_id, @PathVariable(name = "history_id") Long history_id) {
        ModelAndView mav = null;

        if (historyService.isExist(history_id)) {
            mav = new ModelAndView("history/edit");

            mav.getModelMap().addAttribute("title", "Редактирование записи в истории пользователя " + user_id);
            mav.getModelMap().addAttribute("user_id", user_id);

            mav.addObject("history", historyService.get(history_id));
        }
        else
            mav = new ModelAndView("redirect:/history/" + user_id);

        return mav;
    }

    @RequestMapping(value = "{user_id}/save", method = RequestMethod.POST)
    public String save(@ModelAttribute("history") History history, @PathVariable(name = "user_id") Long user_id) {
        historyService.save(history);

        return "redirect:/history/" + user_id;
    }

    @RequestMapping("{user_id}/delete/{history_id}")
    public String delete(@PathVariable(name = "user_id") Long user_id, @PathVariable(name = "history_id") Long history_id) {
        historyService.del(history_id);

        return "redirect:/history/" + user_id;
    }

}
