package com.dylan.personalhub.controller.admin;


import com.dylan.personalhub.entity.Article;
import com.dylan.personalhub.service.ArticleService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


/**
 * 后台——文章管理
 */
@Controller
@RequestMapping("/admin/article")
public class AdminArticleController {

    private final ArticleService articleService;

    public AdminArticleController(ArticleService articleService){
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
     * 显示编辑页面（新增or修改都需要）
     */
    @GetMapping("/edit/{id}")
    public String editPage(
            @PathVariable Long id,
            Model model
    ){

        model.addAttribute("article", articleService.getById(id));
        return "admin/article/edit";

    }



    /**
     * 保存文章：id 为空则新增，否则更新
     */
    @PostMapping("/save")
    public String save(@Valid Article article, BindingResult result){

        if (result.hasErrors()) {
            return "admin/article/edit";
        }

        if (article.getId() == null) {
            articleService.save(article);
        } else {
            articleService.update(article);
        }

        return "redirect:/admin/article";

    }



        /**
         * 删除文章
         */
        @PostMapping("/delete/{id}")
        public String delete(@PathVariable Long id){

            articleService.delete(id);

            return "redirect:/admin/article";

        }
}