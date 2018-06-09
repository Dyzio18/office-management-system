package com.example.controller;


import com.example.model.Case;
import com.example.model.User;
import com.example.service.CaseService;
import com.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;


@Controller
@RequestMapping("/lawyer")
public class LawyerController {

    @Autowired
    private CaseService caseService;

    @Autowired
    private UserService userService;

    @RequestMapping(value ="/cases",method = RequestMethod.GET)
    public ModelAndView list_items(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());

        ModelAndView view = new ModelAndView();
        List<Case> listOfAllCases = caseService.getAll();
        List<Case> listOfCurrentLawyerCases = new ArrayList<>();
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
}
