package com.example.controller;


import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;


import com.example.model.Case;
import com.example.service.CaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.example.model.User;
import com.example.service.UserService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Controller
public class LoginController {

    @Autowired
    private UserService userService;

    @Autowired
    private CaseService caseService;

    @RequestMapping(value = {"/", "/login"}, method = RequestMethod.GET)
    public ModelAndView login() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login");
        return modelAndView;
    }

    @RequestMapping("/home")
    public String defaultAfterLogin() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String role = auth.getAuthorities().toString();

        if (role.contains("ADMIN")) {
            return "redirect:/admin/home";
        } else if (role.contains("RECORDER")) {
            return "redirect:/recorder/home";
        } else if (role.contains("LAWYER")) {
            return "redirect:/lawyer/home";
        } else if (role.contains("ACCOUNTANT")) {
            return "redirect:/accountant/home";
        } else {
            return "redirect:/access-denied";
        }
    }


    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public ModelAndView registration() {
        ModelAndView modelAndView = new ModelAndView();
        User user = new User();
        modelAndView.addObject("user", user);
        modelAndView.setViewName("registration");
        return modelAndView;
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public ModelAndView createNewUser(@Valid User user, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();
        User userExists = userService.findUserByEmail(user.getEmail());
        if (userExists != null) {
            bindingResult
                    .rejectValue("email", "error.user",
                            "There is already a user registered with the email provided");
        }
        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("registration");
        } else {
            userService.saveUser(user);
            modelAndView.addObject("successMessage", "User has been registered successfully");
            modelAndView.addObject("user", new User());
            modelAndView.setViewName("registration");

        }
        return modelAndView;
    }

    @RequestMapping(value = "/admin/home", method = RequestMethod.GET)
    public ModelAndView home() {
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        modelAndView.addObject("userName", user.getName() + " " + user.getLastName());
        modelAndView.addObject("adminMessage", "Content Available Only for Users with Admin Role");
        modelAndView.setViewName("admin/home");
        return modelAndView;
    }

    @RequestMapping(value = "/recorder/home", method = RequestMethod.GET)
    public ModelAndView home__recorder() {
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        modelAndView.addObject("userName", "Welcome " + user.getName() + " " + user.getLastName() + " (" + user.getEmail() + ")");
        modelAndView.addObject("adminMessage", "Content Available Only for Users with Recorder Role");
        modelAndView.setViewName("recorder/home");
        return modelAndView;
    }

    @RequestMapping(value = "/accountant/home", method = RequestMethod.GET)
    public ModelAndView home_accountant() {
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        modelAndView.addObject("userName", user.getName() + " " + user.getLastName());
        modelAndView.addObject("adminMessage", "Content Available Only for Users with Accountant Role");
        modelAndView.setViewName("accountant/home");
        return modelAndView;
    }

    @RequestMapping(value = "/lawyer/home", method = RequestMethod.GET)
    public ModelAndView home_lawyer() {
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());

        LocalDate date = LocalDate.now();

        DateTimeFormatter formatters = DateTimeFormatter.ofPattern("u-MM-dd");
        String textDate = date.format(formatters);

        List<Case> allCases = caseService.getAll();
        List<Case> currentDayCases = new ArrayList<>();
        Collections.sort(allCases, Case.getComparator());
        for (Case c : allCases) {
            if (c.lawyerId == user.getId() && c.getCaseDate().equals(textDate)) {
                currentDayCases.add(c);
            }
        }

        modelAndView.addObject("userName", user.getName() + " " + user.getLastName());
        modelAndView.addObject("listOfCases", currentDayCases);
        modelAndView.addObject("adminMessage", "Content Available Only for Users with Lawyer Role");
        modelAndView.setViewName("lawyer/home");
        return modelAndView;
    }

}
