package com.example.controller;

import com.example.model.User;
import com.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.*;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserService userService;

    @RequestMapping(value="/add_employee", method = RequestMethod.GET)
    public ModelAndView add_employee(){
        ModelAndView modelAndView = new ModelAndView();
        User user = new User();
        modelAndView.addObject("user", user);
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User authUser = userService.findUserByEmail(auth.getName());
        modelAndView.addObject("userName", authUser.getName() + " " + authUser.getLastName());
        modelAndView.setViewName("/admin/add_employee");
        return modelAndView;
    }

    @RequestMapping(value = "/add_employee", method = RequestMethod.POST)
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

    @RequestMapping(value="/all_users", method = RequestMethod.GET)
    public ModelAndView all_users(){
        ModelAndView modelAndView = new ModelAndView();
        List <User> listOfUsers = userService.getAll();
        modelAndView.addObject("listOfUser", listOfUsers);
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User authUser = userService.findUserByEmail(auth.getName());
        modelAndView.addObject("userName", authUser.getName() + " " + authUser.getLastName());
        modelAndView.setViewName("/admin/all_users");
        return modelAndView;
    }

    @RequestMapping(value="/employee_view/{id}", method = RequestMethod.GET)
    public ModelAndView employee_view(@PathVariable Integer id) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("user", userService.findById(id));
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User authUser = userService.findUserByEmail(auth.getName());
        modelAndView.addObject("userName", authUser.getName() + " " + authUser.getLastName());
        modelAndView.setViewName("/admin/employee_view");
        return modelAndView;
    }

    @RequestMapping(value="/employee_view/edit/{id}", method = RequestMethod.GET)
    public ModelAndView update(@PathVariable Integer id){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("user", userService.findById(id));
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User authUser = userService.findUserByEmail(auth.getName());
        modelAndView.addObject("userName", authUser.getName() + " " + authUser.getLastName());
        modelAndView.setViewName("admin/edit");
        return modelAndView;
    }

    @RequestMapping(value="/edit/{id}", method = RequestMethod.POST)
    public ModelAndView do_update(@Valid User u, BindingResult bindingResult){
        userService.updateUser(u);
        return new ModelAndView("redirect:/admin/all_users");
    }

    @RequestMapping(value="/employee_view/delete/{id}", method = RequestMethod.GET)
    public ModelAndView delete_confirm(@PathVariable Integer id){
        ModelAndView modelAndView = new ModelAndView();
        User userFound = userService.findById(id);
        userFound.getEmail();//czy na pewno email??
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User authUser = userService.findUserByEmail(auth.getName());
        modelAndView.addObject("userName", authUser.getName() + " " + authUser.getLastName());
        modelAndView.setViewName("/admin/delete_confirm");
        return modelAndView;
    }

    @RequestMapping(value="/do_delete", method = RequestMethod.POST)
    public ModelAndView do_delete(@Valid User u, BindingResult bindingResult){
        userService.deleteUser(u);
        return new ModelAndView("redirect:/admin/all_users");
    }
}