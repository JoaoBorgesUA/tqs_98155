package com.example.HW1.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class View {

    @GetMapping("/index")
    public String index(Model model) {
        return "index";
    }

}
