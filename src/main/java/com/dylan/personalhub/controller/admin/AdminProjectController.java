package com.dylan.personalhub.controller.admin;


import com.dylan.personalhub.entity.Project;
import com.dylan.personalhub.service.ProjectService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


/**
 * 后台——项目管理
 */
@Controller
@RequestMapping("/admin/project")
public class AdminProjectController {

    private final ProjectService projectService;

    public AdminProjectController(ProjectService projectService){
        this.projectService = projectService;
    }


    /**
     * 项目列表
     */
    @GetMapping
    public String list(Model model){

        model.addAttribute("projects", projectService.getAll());
        return "admin/project/list";

    }


    /**
     * 新增页面
     */
    @GetMapping("/create")
    public String createPage(Model model){

        model.addAttribute("project", new Project());
        return "admin/project/edit";

    }


    /**
     * 编辑页面
     */
    @GetMapping("/edit/{id}")
    public String editPage(
            @PathVariable Long id,
            Model model
    ){

        model.addAttribute("project", projectService.getById(id));
        return "admin/project/edit";

    }


    /**
     * 保存项目：id 为空则新增，否则更新
     */
    @PostMapping("/save")
    public String save(@Valid Project project, BindingResult result){

        if (result.hasErrors()) {
            return "admin/project/edit";
        }

        if (project.getId() == null) {
            projectService.save(project);
        } else {
            projectService.update(project);
        }

        return "redirect:/admin/project";

    }


    /**
     * 删除项目
     */
    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Long id){

        projectService.delete(id);

        return "redirect:/admin/project";

    }

}