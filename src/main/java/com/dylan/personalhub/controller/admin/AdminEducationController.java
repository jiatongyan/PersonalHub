package com.dylan.personalhub.controller.admin;


import com.dylan.personalhub.entity.Education;
import com.dylan.personalhub.service.EducationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


/**
 * 后台——教育经历管理
 */

@Controller
@RequestMapping("/admin/education")
public class AdminEducationController {

    private final EducationService educationService;

    public AdminEducationController(EducationService educationService){
        this.educationService = educationService;
    }


    /**
     * 教育经历列表
     */
    @GetMapping
    public String list(Model model){

        model.addAttribute("educations", educationService.getAll());
        return "admin/education/list";

    }


    /**
     * 新增页面
     */
    @GetMapping("/create")
    public String createPage(Model model){

        model.addAttribute("education", new Education());
        return "admin/education/edit";

    }


    /**
     * 编辑页面
     */
    @GetMapping("/edit/{id}")
    public String editPage(
            @PathVariable Long id,
            Model model
    ){

        model.addAttribute("education", educationService.getById(id));
        return "admin/education/edit";

    }


    /**
     * 保存教育经历：id 为空则新增，否则更新
     */
    @PostMapping("/save")
    public String save(Education education){

        if (education.getId() == null) {
            educationService.save(education);
        } else {
            educationService.update(education);
        }

        return "redirect:/admin/education";

    }


    /**
     * 删除教育经历
     */
    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Long id){

        educationService.delete(id);

        return "redirect:/admin/education";

    }

}
