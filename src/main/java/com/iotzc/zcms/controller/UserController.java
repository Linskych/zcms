package com.iotzc.zcms.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {

    @RequestMapping("/list")
    public String login() {
        return "index";
    }
//    
//    @RequestMapping("/check")
//    public String check() {
//        
//    }
}
