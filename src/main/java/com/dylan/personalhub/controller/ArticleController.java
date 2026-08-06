package com.dylan.personalhub.controller;

import com.dylan.personalhub.service.ArticleService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
public class ArticleController {


    private final ArticleService articleService;


    public ArticleController(
            ArticleService articleService
    ){

        this.articleService=articleService;

    }



    @GetMapping("/article/{id}")
    public String detail(
            @PathVariable Long id,
            Model model
    ){


        model.addAttribute(
                "article",
                articleService.getById(id)
        );

        return "article";

    }


}