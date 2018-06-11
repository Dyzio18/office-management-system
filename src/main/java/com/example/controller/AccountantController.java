package com.example.controller;

import com.example.model.Item;
import com.example.model.User;
import com.example.service.ItemService;
import com.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;


@Controller
@RequestMapping("/accountant")
public class AccountantController {

    @Autowired
    private ItemService itemService;

    @Autowired
    private UserService userService;

    @RequestMapping(value ="/list_items",method = RequestMethod.GET)
    public ModelAndView list_items(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        ModelAndView view = new ModelAndView();
        List<Item> listOfItems = itemService.getAll();
        view.addObject("listOfItems", listOfItems);
        view.addObject("userName", user.getName() + " " + user.getLastName());
        view.setViewName("/accountant/list_items");
        return view;
    }

    @RequestMapping(value="/add_item", method = RequestMethod.GET)
    public ModelAndView add_item(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        ModelAndView view = new ModelAndView();
        Item item = new Item();
        view.addObject("item", item);
        view.addObject("userName", user.getName() + " " + user.getLastName());
        view.setViewName("/accountant/add_item");
        return view;
    }

    @RequestMapping(value = "/add_item", method = RequestMethod.POST)
    public ModelAndView createNewItem(@Valid Item item, BindingResult bindingResult) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        ModelAndView view = new ModelAndView();
        if (bindingResult.hasErrors()) {
            view.addObject("userName", user.getName() + " " + user.getLastName());
            view.setViewName("accountant/add_item");
        } else {
            itemService.saveItem(item);
            view.addObject("item", new Item());
            view.addObject("userName", user.getName() + " " + user.getLastName());
            view.setViewName("accountant/add_item");
        }
        return view;
    }

    @RequestMapping(value="/edit_item/{id}", method = RequestMethod.GET)
    public ModelAndView update_item(@PathVariable Integer id){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        ModelAndView view = new ModelAndView();
        view.addObject("item", itemService.findById(id));
        view.addObject("userName", user.getName() + " " + user.getLastName());
        view.setViewName("accountant/edit_item");
        return view;
    }

    @RequestMapping(value="/edit_item/{id}", method = RequestMethod.POST)
    public ModelAndView do_update_item(@Valid Item item, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            ModelAndView view = new ModelAndView();
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            User user = userService.findUserByEmail(auth.getName());
            view.addObject("item", item);
            view.addObject("userName", user.getName() + " " + user.getLastName());
            view.setViewName("/accountant/edit_item");
            return view;
        } else {
            itemService.saveItem(item);
            return new ModelAndView("redirect:/accountant/list_items");
        }
    }



}
