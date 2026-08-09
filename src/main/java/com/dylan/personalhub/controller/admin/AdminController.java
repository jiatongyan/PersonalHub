package com.dylan.personalhub.controller.admin;

import com.dylan.personalhub.entity.AdminUser;
import com.dylan.personalhub.service.AdminService;
import jakarta.servlet.http.HttpSession;
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
    public String loginPage(HttpSession session) {
        // 已登录则直接进入后台
        if (session.getAttribute("adminUser") != null) {
            return "redirect:/admin/dashboard";
        }
        return "admin/login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String username,
                        @RequestParam String password,
                        Model model,
                        HttpSession session) {
        AdminUser user = service.login(username, password);

        if (user != null) {
            session.setAttribute("adminUser", user);
            return "redirect:/admin/dashboard";
        }

        model.addAttribute("error", "账号或密码错误");
        return "admin/login";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/admin/login";
    }

    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        return "admin/dashboard";
    }
}