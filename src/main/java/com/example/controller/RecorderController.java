package com.example.controller;


import com.example.model.Case;
import com.example.service.CaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;


@Controller
@RequestMapping("/recorder")
public class RecorderController {

    @Autowired
    private CaseService caseService;

    @RequestMapping(value ="/cases",method = RequestMethod.GET)
    public ModelAndView list_items(){
        ModelAndView view = new ModelAndView();
        List<Case> listOfCases = caseService.getAll();
        view.addObject("listOfCases", listOfCases);
        view.setViewName("/recorder/cases");
        return view;
    }

    @RequestMapping(value="/add_case", method = RequestMethod.GET)
    public ModelAndView add_case(){
        ModelAndView modelAndView = new ModelAndView();
        Case item = new Case();
        modelAndView.addObject("item", new Case());
        modelAndView.setViewName("/recorder/add_case");
        return modelAndView;
    }

    @RequestMapping(value = "/add_case", method = RequestMethod.POST)
    public ModelAndView add_case(@Valid Case item, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();
        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("recorder/add_case");
        } else {
            caseService.saveCase(item);
            modelAndView.addObject("successMessage", "Dodano nową sprawe");
            modelAndView.addObject("item", new Case());
            modelAndView.setViewName("recorder/add_case");
        }
        return modelAndView;
    }



}
