package com.iotzc.zcms.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {

    @RequestMapping("/test")
    public String test() {
        return "views/user/login";
    }
    
    @RequestMapping("/login")
    public String login() {
        return "views/user/login";
    }
}
