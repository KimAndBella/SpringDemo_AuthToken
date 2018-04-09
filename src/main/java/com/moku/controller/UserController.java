package com.moku.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by pan.sun on 2018/4/9.
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @GetMapping("/success")
    public String success(){
        return "Login Success";
    }
}
