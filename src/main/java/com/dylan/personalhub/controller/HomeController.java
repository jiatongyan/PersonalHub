package com.dylan.personalhub.controller;

import com.dylan.personalhub.service.PersonService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {


    private final PersonService personService;


    public HomeController(PersonService personService){

        this.personService = personService;

    }


    @GetMapping("/")
    public String index(Model model){


        model.addAttribute(
                "person",
                personService.getPerson()
        );


        return "index";

    }

}