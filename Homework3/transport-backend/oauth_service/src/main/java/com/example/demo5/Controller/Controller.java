package com.example.demo5.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@org.springframework.stereotype.Controller

public class Controller {
    @GetMapping("/")
    public String index() {
        return "index";
    }
}
