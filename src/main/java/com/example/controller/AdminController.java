package com.example.controller;

import com.example.model.User;
import com.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.*;

@Controller
public class AdminController {

    @Autowired
    private UserService userService;

    @RequestMapping(value="/admin/add_employee", method = RequestMethod.GET)
    public ModelAndView add_employee(){
        ModelAndView modelAndView = new ModelAndView();
        User user = new User();
        modelAndView.addObject("user", user);
        modelAndView.setViewName("/admin/add_employee");
        return modelAndView;
    }

    @RequestMapping(value = "/admin/add_employee", method = RequestMethod.POST)
    public ModelAndView createNewUser(@Valid User user, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();
        User userExists = userService.findUserByEmail(user.getEmail());
        if (userExists != null) {
            bindingResult
                    .rejectValue("email", "error.user",
                            "There is already a user registered with the email provided");
        }
        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("admin/add_employee");
        } else {
            userService.saveUser(user);
            modelAndView.addObject("successMessage", "User has been registered successfully");
            modelAndView.addObject("user", new User());
            modelAndView.setViewName("admin/add_employee");

        }
        return modelAndView;
    }

    @RequestMapping(value="/admin/all_users", method = RequestMethod.GET)
    public ModelAndView all_users(){
        ModelAndView modelAndView = new ModelAndView();
//        User user = new User();
        List <User> listOfUsers = userService.getAll();
        modelAndView.addObject("listOfUser", listOfUsers);
        modelAndView.setViewName("/admin/all_users");
        return modelAndView;
    }
}