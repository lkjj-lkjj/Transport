package com.example.transport.controller;

import com.example.transport.entity.Contract;
import com.example.transport.entity.User;
import com.example.transport.service.ContractService;
import com.example.transport.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class ContractController {
    @Autowired
    private ContractService contractService;
    @Autowired
    private UserService userService;
    @GetMapping("/index")
    public String toIndexView(Model model){
        List<Contract> contracts = contractService.findAllContracts();
        List<User> users = userService.findAllUsers();
        model.addAttribute("contracts", contracts);
        model.addAttribute("users", users);
        return "index";
    }
}
