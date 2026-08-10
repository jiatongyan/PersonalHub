package com.dylan.personalhub.controller.admin;

import com.dylan.personalhub.entity.AdminUser;
import com.dylan.personalhub.service.AdminService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

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
                        HttpServletRequest request) {
        AdminUser user = service.login(username, password);

        if (user != null) {
            // 防止会话固定攻击：销毁旧 session，创建新的
            HttpSession oldSession = request.getSession(false);
            if (oldSession != null) {
                oldSession.invalidate();
            }
            HttpSession newSession = request.getSession(true);
            newSession.setAttribute("adminUser", user);
            // 生成 CSRF Token
            newSession.setAttribute("csrfToken", UUID.randomUUID().toString());
            return "redirect:/admin/dashboard";
        }

        model.addAttribute("error", "账号或密码错误");
        return "admin/login";
    }

    @PostMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/admin/login";
    }

    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        return "admin/dashboard";
    }
}