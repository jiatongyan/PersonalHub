package com.dylan.personalhub.controller;

import com.dylan.personalhub.service.*;
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
    private final ProjectService projectService;
    private final ArticleService articleService;

    public HomeController(
            PersonService personService,
            EducationService educationService,
            ExperienceService experienceService,
            SkillService skillService,
            ProjectService projectService,
            ArticleService articleService
    ){
        this.personService = personService;
        this.educationService = educationService;
        this.experienceService = experienceService;
        this.skillService = skillService;
        this.projectService = projectService;
        this.articleService = articleService;
    }


    @GetMapping("/")
    public String index(Model model){


        // person 表可能为空，提供空对象防止模板 空指针异常
        var person = personService.getPerson();
        if (person == null) {
            person = new com.dylan.personalhub.entity.Person();
        }
        model.addAttribute("person", person);


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

        model.addAttribute(
                "projects",
                projectService.getAll()
        );

        model.addAttribute(
                "articles",
                articleService.getAll()
        );

        return "index";

    }

}