package com.dylan.personalhub.controller.admin;


import com.dylan.personalhub.entity.Skill;
import com.dylan.personalhub.service.SkillService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


/**
 * 后台——技能管理
 */
@Controller
@RequestMapping("/admin/skill")
public class AdminSkillController {

    private final SkillService skillService;

    public AdminSkillController(SkillService skillService){
        this.skillService = skillService;
    }


    /**
     * 技能列表
     */
    @GetMapping
    public String list(Model model){

        model.addAttribute("skills", skillService.getAll());
        return "admin/skill/list";

    }


    /**
     * 新增页面
     */
    @GetMapping("/create")
    public String createPage(Model model){

        model.addAttribute("skill", new Skill());
        return "admin/skill/edit";

    }


    /**
     * 编辑页面
     */
    @GetMapping("/edit/{id}")
    public String editPage(
            @PathVariable Long id,
            Model model
    ){

        model.addAttribute("skill", skillService.getById(id));
        return "admin/skill/edit";

    }


    /**
     * 保存技能：id 为空则新增，否则更新
     */
    @PostMapping("/save")
    public String save(@Valid Skill skill, BindingResult result){

        if (result.hasErrors()) {
            return "admin/skill/edit";
        }

        if (skill.getId() == null) {
            skillService.save(skill);
        } else {
            skillService.update(skill);
        }

        return "redirect:/admin/skill";

    }


    /**
     * 删除技能
     */
    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Long id){

        skillService.delete(id);

        return "redirect:/admin/skill";

    }

}
