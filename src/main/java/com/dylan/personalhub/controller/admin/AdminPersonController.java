package com.dylan.personalhub.controller.admin;


import com.dylan.personalhub.entity.Person;
import com.dylan.personalhub.service.PersonService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


/**
 * 后台——个人信息管理
 * Person 为单条记录，无需列表页，直接编辑
 */

@Controller
@RequestMapping("/admin/person")
public class AdminPersonController {

    private final PersonService personService;

    public AdminPersonController(PersonService personService){
        this.personService = personService;
    }


    /**
     * 个人信息编辑页面
     */
    @GetMapping
    public String editPage(Model model){

        Person person = personService.getPerson();

        if (person == null) {
            person = new Person();
        }

        model.addAttribute("person", person);
        return "admin/person/edit";

    }


    /**
     * 保存个人信息：id 为空则新增，否则更新
     */
    @PostMapping("/save")
    public String save(@Valid Person person, BindingResult result){

        if (result.hasErrors()) {
            return "admin/person/edit";
        }

        if (person.getId() == null) {
            personService.save(person);
        } else {
            personService.update(person);
        }

        return "redirect:/admin/person";

    }

}
