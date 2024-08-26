package com.hit_chess.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/home")
    public String home(){
        return "index";
    }

    @GetMapping("/hit")
    public String hitWicket(){
        return "hitindex";
    }
}
