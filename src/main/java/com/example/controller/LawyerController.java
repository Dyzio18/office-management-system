package com.example.controller;


import com.example.model.Case;
import com.example.model.User;
import com.example.service.CaseService;
import com.example.service.UserService;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


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

    @GetMapping("/case_view/{id}")
    public ModelAndView editCase(@PathVariable Long id){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        ModelAndView view = new ModelAndView();
        Case myCase = caseService.findById(id);
        view.addObject("case", myCase);;
        view.addObject("userName", user.getName() + " " + user.getLastName());
        view.setViewName("/lawyer/case_view");
        return view;
    }

    @PostMapping("/case_view")
    public ModelAndView editCase(@Valid Case c, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            ModelAndView view = new ModelAndView();
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            User user = userService.findUserByEmail(auth.getName());
            view.addObject("case", c);
            view.addObject("userName", user.getName() + " " + user.getLastName());
            view.setViewName("/lawyer/case_view");
            return view;
        } else {
            caseService.saveCase(c);
            return new ModelAndView("redirect:/lawyer/cases");
        }
    }

    @RequestMapping(value ="/calendar", method = RequestMethod.GET)
    public ModelAndView calendar(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        ModelAndView view = new ModelAndView();
        view.addObject("userName", user.getName() + " " + user.getLastName());
        view.setViewName("/lawyer/calendar");
        return view;
    }

    @RequestMapping(value ="/calendar/events.json", method = RequestMethod.GET)
    @ResponseBody
    public EventsResponse calendarEvents() throws ParseException {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());

        List<Case> listOfAllCases = caseService.getAll();
        List<Event> events = new ArrayList<>();
        for (Case c : listOfAllCases) {
            if (c.lawyerId == user.getId()) {
                Event event = new Event();
                event.setId(c.getCaseId());
                event.setTitle(c.getClientName() + " " + c.getClientSurname() + " - " + c.getCaseNote() + " - " + c.getCaseTime());
                event.setUrl("/lawyer/case_view/" + c.getCaseId());
                event.setClassName("event-info");

                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                Date caseDate =  dateFormat.parse(c.getCaseDate() + " " + c.getCaseTime());
                event.setStart(caseDate.getTime());
                event.setEnd(caseDate.getTime());

                events.add(event);
            }
        }

        EventsResponse response = new EventsResponse();
        response.setSuccess(1);
        response.setResult(events);
        return response;
    }

    class EventsResponse {
        private Integer success;
        private List<Event> result;

        public Integer getSuccess() {
            return success;
        }

        public void setSuccess(Integer success) {
            this.success = success;
        }

        public List<Event> getResult() {
            return result;
        }

        public void setResult(List<Event> result) {
            this.result = result;
        }


    }

    class Event {

        private Long id;

        private String title;

        private String url;

        @JsonProperty("class")
        private String className;

        private Long start;

        private Long end;

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getClassName() {
            return className;
        }

        public void setClassName(String className) {
            this.className = className;
        }

        public Long getStart() {
            return start;
        }

        public void setStart(Long start) {
            this.start = start;
        }

        public Long getEnd() {
            return end;
        }

        public void setEnd(Long end) {
            this.end = end;
        }
    }

}
