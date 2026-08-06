package com.dylan.personalhub.controller;

import com.dylan.personalhub.service.EducationService;
import com.dylan.personalhub.service.ExperienceService;
import com.dylan.personalhub.service.PersonService;
import com.dylan.personalhub.service.SkillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {


    private final PersonService personService;
    private final EducationService educationService;
    private final ExperienceService experienceService;
    private final SkillService skillService;

    public HomeController(
            PersonService personService,
            EducationService educationService,
            ExperienceService experienceService,
            SkillService skillService
    ){
        this.personService = personService;
        this.educationService = educationService;
        this.experienceService = experienceService;
        this.skillService = skillService;
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


        model.addAttribute(
                "experiences",
                experienceService.getAll()
        );

        model.addAttribute(
                "skills",
                skillService.getAll()
        );

        return "index";

    }

}