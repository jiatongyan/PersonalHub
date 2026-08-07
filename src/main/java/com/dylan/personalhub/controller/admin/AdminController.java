package com.dylan.personalhub.controller.admin;

import com.dylan.personalhub.service.AdminService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final AdminService service;

    public AdminController(AdminService service) {
        this.service = service;
    }

    @GetMapping("/login")
    public String loginPage() {
        return "admin/login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String username,
                        @RequestParam String password,
                        Model model) {
        if (service.login(username, password)) {
            return "redirect:/admin/dashboard";
        }

        model.addAttribute("error", "账号或密码错误");
        return "admin/login";
    }

    @GetMapping("/dashboard")
    public String dashboard(Model model){
        return "admin/dashboard";
    }
}