package com.dylan.personalhub.controller.admin;


import com.dylan.personalhub.entity.Article;
import com.dylan.personalhub.service.ArticleService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;



@Controller
@RequestMapping("/admin/article")
public class AdminArticleController {


    private final ArticleService articleService;


    public AdminArticleController(
            ArticleService articleService
    ){

        this.articleService = articleService;

    }



    /**
     * 文章列表
     */
    @GetMapping
    public String list(Model model){

        model.addAttribute("articles", articleService.findAll());
        return "admin/article/list";
    }



    /**
     * 新建页面
     */
    @GetMapping("/create")
    public String createPage(Model model){

        model.addAttribute("article", new Article());
        return "admin/article/edit";

    }



    /**
     * 编辑页面
     */
    @GetMapping("/edit/{id}")
    public String editPage(
            @PathVariable Long id,
            Model model
    ){

        model.addAttribute(
                "article",
                articleService.getById(id)
        );

        return "admin/article/edit";

    }



    /**
     * 保存文章：id 为空则新增，否则更新
     */
    @PostMapping("/save")
    public String save(Article article){

        if (article.getId() == null) {
            articleService.save(article);
        } else {
            articleService.update(article);
        }

        return "redirect:/admin/article";

    }



}