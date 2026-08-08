package com.dylan.personalhub.controller.admin;


import com.dylan.personalhub.entity.Experience;
import com.dylan.personalhub.service.ExperienceService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


/**
 * 后台——工作经历管理
 */
@Controller
@RequestMapping("/admin/experience")
public class AdminExperienceController {

    private final ExperienceService experienceService;

    public AdminExperienceController(ExperienceService experienceService){
        this.experienceService = experienceService;
    }


    /**
     * 经历列表
     */
    @GetMapping
    public String list(Model model){

        model.addAttribute("experiences", experienceService.getAll());
        return "admin/experience/list";

    }


    /**
     * 新增页面
     */
    @GetMapping("/create")
    public String createPage(Model model){

        model.addAttribute("experience", new Experience());
        return "admin/experience/edit";

    }


    /**
     * 编辑页面
     */
    @GetMapping("/edit/{id}")
    public String editPage(
            @PathVariable Long id,
            Model model
    ){

        model.addAttribute("experience", experienceService.getById(id));
        return "admin/experience/edit";

    }


    /**
     * 保存经历：id 为空则新增，否则更新
     */
    @PostMapping("/save")
    public String save(Experience experience){

        if (experience.getId() == null) {
            experienceService.save(experience);
        } else {
            experienceService.update(experience);
        }

        return "redirect:/admin/experience";

    }


    /**
     * 删除经历
     */
    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Long id){

        experienceService.delete(id);

        return "redirect:/admin/experience";

    }

}
