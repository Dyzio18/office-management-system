package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@Controller
public class RecorderController {

    @RequestMapping(value="/recorder/add-case", method = RequestMethod.GET)
    public ModelAndView add_case(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/recorder/add-case");
        return modelAndView;
    }

}
