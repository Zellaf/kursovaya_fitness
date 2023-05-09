package com.fitness.controllers;

import com.fitness.entities.Subscription;
import com.fitness.services.SubscriptionService;
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
@RequestMapping("/subscription")
public class SubscriptionController {

    @Autowired
    private SubscriptionService subscriptionService;

    @RequestMapping({"", "/"})
    public String index(Model model, @Param("search_keyword") String search_keyword) {
        model.addAttribute("title", "Абонементы");
        model.addAttribute("search_keyword", search_keyword);

        List<Subscription> subscriptionList = null;

        if (search_keyword != null)
            subscriptionList = subscriptionService.findAll(search_keyword);
        else
            subscriptionList = subscriptionService.findAll();

        model.addAttribute("subscription_list", subscriptionList);

        return "subscription/index";
    }

    @RequestMapping("/create")
    public ModelAndView create() {
        ModelAndView mav = new ModelAndView("subscription/create");

        mav.getModelMap().addAttribute("title", "Добавить абонемент");
        mav.addObject("subscription", new Subscription());

        return mav;
    }

    @RequestMapping("/edit/{subscription_id}")
    public ModelAndView edit(@PathVariable(name = "subscription_id") Long subscription_id) {
        ModelAndView mav = null;

        if (subscriptionService.isExist(subscription_id)) {
            mav = new ModelAndView("subscription/edit");

            mav.getModelMap().addAttribute("title", "Редактирование подписки " + subscription_id);
            mav.getModelMap().addAttribute("subscription_id", subscription_id);

            mav.addObject("subscription", subscriptionService.get(subscription_id));
        }
        else
            mav = new ModelAndView("redirect:/subscription");

        return mav;
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(@ModelAttribute("subscription") Subscription subscription) {
        subscriptionService.save(subscription);

        return "redirect:/subscription";
    }

    @RequestMapping("/delete/{subscription_id}")
    public String delete(@PathVariable(name = "subscription_id") Long subscription_id) {
        subscriptionService.del(subscription_id);

        return "redirect:/subscription";
    }

}
