package com.example.controller;


import com.example.model.Case;
import com.example.model.User;
import com.example.service.CaseService;
import com.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


@Controller
@RequestMapping("/lawyer")
public class LawyerController {

    @Autowired
    private CaseService caseService;

    @Autowired
    private UserService userService;

    @RequestMapping(value ="/cases",method = RequestMethod.GET)
    public ModelAndView currentCases(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());

        ModelAndView view = new ModelAndView();
        List<Case> listOfAllCases = caseService.getAll();
        List<Case> listOfCurrentLawyerCases = new ArrayList<>();
        Collections.sort(listOfAllCases, Case.getComparator());
        for (Case c : listOfAllCases) {
            if (c.lawyerId == user.getId()) {
                listOfCurrentLawyerCases.add(c);
            }
        }
        view.addObject("userName", user.getName() + " " + user.getLastName());
        view.addObject("listOfCases", listOfCurrentLawyerCases);
        view.setViewName("/lawyer/cases");
        return view;
    }

    @GetMapping("/edit_case/{id}")
    public ModelAndView editCase(@PathVariable Long id){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        ModelAndView view = new ModelAndView();
        Case myCase = caseService.findById(id);
        view.addObject("case", myCase);
        view.addObject("userName", user.getName() + " " + user.getLastName());
        view.setViewName("/lawyer/edit_case");
        return view;
    }

    @PostMapping("/edit_case")
    public ModelAndView editCase(@Valid Case c, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            ModelAndView view = new ModelAndView();
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            User user = userService.findUserByEmail(auth.getName());
            view.addObject("case", c);
            view.addObject("userName", user.getName() + " " + user.getLastName());
            view.setViewName("/lawyer/edit_case");
            return view;
        } else {
            caseService.saveCase(c);
            return new ModelAndView("redirect:/lawyer/cases");
        }
    }
}
