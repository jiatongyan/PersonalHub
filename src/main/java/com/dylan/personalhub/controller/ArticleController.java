package com.dylan.personalhub.controller;

import com.dylan.personalhub.entity.Article;
import com.dylan.personalhub.service.ArticleService;
import com.dylan.personalhub.service.MarkdownService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
public class ArticleController {


    private final ArticleService articleService;

    @Autowired
    private MarkdownService markdownService;


    public ArticleController(
            ArticleService articleService
    ){

        this.articleService=articleService;

    }



    @GetMapping("/article/{id}")
    public String article(@PathVariable Long id, Model model){

        Article article = articleService.getById(id);

        String html = markdownService.render(article.getContent());

        model.addAttribute("article", article);

        model.addAttribute("content", html);

        return "article";

    }


}