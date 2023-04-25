package com.example.transport.controller;

import com.example.transport.entity.User;
import com.example.transport.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Objects;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public String showLoginForm(Model model){
        model.addAttribute("user", new User());
        return "login";
    }

    @GetMapping("/register")
    public String toRegisterView(Model model){
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute("user") User user, Model model){
        User result = userService.findByUsernameAndPassword(user.getUsername(), user.getPassword());
        if(result != null){
            return "redirect:/index";
        }else{
            model.addAttribute("error", "Invalid username or password");
            return "login";
        }
    }

    @PostMapping("/register")
    public String register(@ModelAttribute("user") User user,
                           @RequestParam("confirm") String confirm, Model model, RedirectAttributes redirectAttributes){
        if(!Objects.equals(user.getPassword(), confirm)){
            model.addAttribute("error", "Password not match");
            return "register";
        }
        userService.registerAuthUser(user);
        redirectAttributes.addFlashAttribute("success", "Register success");
        return "redirect:/login";
    }
}
