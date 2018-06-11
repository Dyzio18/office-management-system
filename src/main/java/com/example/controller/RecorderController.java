package com.example.controller;


import com.example.model.Case;
import com.example.model.Client;

import com.example.model.Item;
import com.example.model.User;
import com.example.service.CaseService;
import com.example.service.ClientService;
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
@RequestMapping("/recorder")
public class RecorderController {

    @Autowired
    private CaseService caseService;

    @Autowired
    private ClientService clientService;

    @Autowired
    private UserService userService;

    @RequestMapping(value ="/clients",method = RequestMethod.GET)
    public ModelAndView list_clients(){
        ModelAndView view = new ModelAndView();

        // LOGIN
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        view.addObject("userName", user.getName() + " " + user.getLastName());

        List<Client> listOfClients;
        listOfClients = clientService.getAll();
        view.addObject("listOfClients", listOfClients);
        view.setViewName("/recorder/clients");
        return view;
    }

    @RequestMapping(value="/client_show/{id}", method = RequestMethod.GET)
    public ModelAndView show_client(@PathVariable Integer id){
        ModelAndView modelAndView = new ModelAndView();

        // LOGIN
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        modelAndView.addObject("userName", user.getName() + " " + user.getLastName());

        modelAndView.addObject("client", clientService.findById(id));
        modelAndView.setViewName("recorder/client_show");
        return modelAndView;
    }

    @RequestMapping(value="/client_new", method = RequestMethod.GET)
    public ModelAndView add_client(){
        ModelAndView modelAndView = new ModelAndView();

        // LOGIN
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        modelAndView.addObject("userName", user.getName() + " " + user.getLastName());

        Client client = new Client();
        modelAndView.addObject("client", client);
        modelAndView.setViewName("/recorder/client_new");
        return modelAndView;
    }

    @RequestMapping(value = "/client_new", method = RequestMethod.POST)
    public ModelAndView createNewClient(@Valid Client client, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();
        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("recorder/client_new");
        } else {
            clientService.saveClient(client);
            modelAndView.addObject("successMessage", "Nowy klient dodany do systemu");
            modelAndView.addObject("client", new Client());
            modelAndView.setViewName("recorder/client_new");
        }
        return modelAndView;
    }


    @RequestMapping(value ="/cases",method = RequestMethod.GET)
    public ModelAndView list_cases(){
        ModelAndView view = new ModelAndView();

        // LOGIN
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        view.addObject("userName", user.getName() + " " + user.getLastName());

        List<Case> listOfCases = caseService.getAll();
        view.addObject("listOfCases", listOfCases);
        view.setViewName("/recorder/cases");
        return view;
    }

    // Add case for specific client
    @RequestMapping(value="/client_show/{id}/add_case", method = RequestMethod.GET)
    public ModelAndView add_ClientCase(@PathVariable Integer id){
        ModelAndView modelAndView = new ModelAndView();
        Client client = clientService.findById(id);

        Case newCase = new Case();
        newCase.setClientName(client.getC_name());
        newCase.setClientSurname(client.getC_surname());

        // LOGIN
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        modelAndView.addObject("userName", user.getName() + " " + user.getLastName());

        modelAndView.addObject("client", client);
        modelAndView.addObject("case", newCase);
        modelAndView.setViewName("/recorder/add_case");
        return modelAndView;
    }

    /*@RequestMapping(value="/add_case", method = RequestMethod.GET)
    public ModelAndView add_case(){
        ModelAndView modelAndView = new ModelAndView();
        Case item = new Case();
        modelAndView.addObject("item", new Case());
        modelAndView.setViewName("/recorder/add_case");
        return modelAndView;
    }*/

    @RequestMapping(value = "/add_case", method = RequestMethod.POST)
    public ModelAndView add_case(@Valid Case item, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();
        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("recorder/add_case");
        } else {
            caseService.saveCase(item);
           /* modelAndView.addObject("successMessage", "Dodano nową sprawe");
            modelAndView.addObject("item", new Case());*/

            List<Case> listOfCases = caseService.getAll();
            modelAndView.addObject("listOfCases", listOfCases);
            modelAndView.setViewName("/recorder/cases");

        }
        return modelAndView;
    }



}
