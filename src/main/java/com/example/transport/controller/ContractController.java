package com.example.transport.controller;

import com.example.transport.dao.ContractDao;
import com.example.transport.entity.Contract;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class ContractController {
    @Autowired
    private ContractDao contractDao;

    @GetMapping("/index")
    public String toIndexView(Model model){
        List<Contract> contracts = contractDao.findAll();
        model.addAttribute("contracts", contracts);
        return "index";
    }
}
