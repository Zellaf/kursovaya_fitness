package com.fitness.controllers;

import com.fitness.entities.Subscription;
import com.fitness.entities.UserSubscription;
import com.fitness.services.SubscriptionService;
import com.fitness.services.UserService;
import com.fitness.services.UserSubscriptionService;
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
@RequestMapping("/user-subscription")
public class UserSubscriptionController {

    @Autowired
    private UserSubscriptionService userSubscriptionService;

    @Autowired
    private SubscriptionService subscriptionService;

    @Autowired
    private UserService userService;

    @RequestMapping("{user_id}")
    public String index(Model model, @PathVariable(name = "user_id") Long user_id, @Param("search_keyword") String search_keyword) {
        model.addAttribute("title", "Абонементы пользователя " + user_id);
        model.addAttribute("user_id", user_id);
        model.addAttribute("search_keyword", search_keyword);

        List<UserSubscription> userSubscriptionList = null;

        if (search_keyword != null)
            userSubscriptionList = userSubscriptionService.findByUserId(user_id, search_keyword);
        else
            userSubscriptionList = userSubscriptionService.findByUserId(user_id);

        model.addAttribute("userSubscription_list", userSubscriptionList);

        return "user-subscription/index";
    }

    @RequestMapping("{user_id}/create")
    public ModelAndView create(@PathVariable(name = "user_id") Long user_id) {
        ModelAndView mav = new ModelAndView("user-subscription/create");

        mav.getModelMap().addAttribute("title", "Добавить абонемент пользователю " + user_id);
        mav.getModelMap().addAttribute("user_id", user_id);

        mav.addObject("user_subscription", new UserSubscription(userService.findUserById(user_id)));

        List<Subscription> subscriptionList = subscriptionService.findAll();
        mav.getModelMap().addAttribute("subscription_list", subscriptionList);

        return mav;
    }

    @RequestMapping("{user_id}/edit/{user_subscription_id}")
    public ModelAndView edit(@PathVariable(name = "user_id") Long user_id, @PathVariable(name = "user_subscription_id") Long user_subscription_id) {
        ModelAndView mav = null;

        if (userSubscriptionService.isExist(user_subscription_id)) {
            mav = new ModelAndView("user-subscription/edit");

            mav.getModelMap().addAttribute("title", "Редактирование абонемента пользователя " + user_id);
            mav.getModelMap().addAttribute("user_id", user_id);

            mav.addObject("user_subscription", userSubscriptionService.get(user_subscription_id));
        } else
            mav = new ModelAndView("redirect:/user-subscription/" + user_id);

        return mav;
    }

    @RequestMapping(value = "{user_id}/save", method = RequestMethod.POST)
    public String save(@ModelAttribute("user_subscription") UserSubscription userSubscription, @PathVariable(name = "user_id") Long user_id) {
        userSubscriptionService.save(userSubscription);

        return "redirect:/user-subscription/" + user_id;
    }

    @RequestMapping("{user_id}/delete/{user_subscription_id}")
    public String delete(@PathVariable(name = "user_id") Long user_id, @PathVariable(name = "user_subscription_id") Long user_subscription_id) {
        userSubscriptionService.del(user_subscription_id);

        return "redirect:/user-subscription/" + user_id;
    }

}
