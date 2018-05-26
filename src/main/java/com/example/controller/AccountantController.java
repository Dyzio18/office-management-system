package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping("/accountant")
public class AccountantController {

    @RequestMapping(value ="/list",method = RequestMethod.GET)
    public ModelAndView getList(){
        ModelAndView view = new ModelAndView();
        return view;
    }


}
