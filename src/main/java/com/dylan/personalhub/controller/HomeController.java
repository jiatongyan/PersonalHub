package com.dylan.personalhub.controller;

import com.dylan.personalhub.service.EducationService;
import com.dylan.personalhub.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {


    private final PersonService personService;

    private final EducationService educationService;


    public HomeController(
            PersonService personService,
            EducationService educationService
    ){

        this.personService = personService;
        this.educationService = educationService;
    }


    @GetMapping("/")
    public String index(Model model){


        model.addAttribute(
                "person",
                personService.getPerson()
        );

        model.addAttribute(
                "educations",
                educationService.getAll()
        );

        return "index";

    }

}