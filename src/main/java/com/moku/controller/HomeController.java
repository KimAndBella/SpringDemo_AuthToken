package com.moku.controller;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletException;
import java.util.Date;

/**
 * Created by pan.sun on 2018/4/9.
 */
@RestController
@RequestMapping("/")
public class HomeController {
    @PostMapping("/login")
    public String login(@RequestParam("username")String name,@RequestParam("password")String password) throws ServletException {
        String token = "";
        if(!"admin".equals(name)){
            throw new ServletException("没有该用户");
        }
        if(!"123".equals(password)){
            throw new ServletException("密码错误");
        }
        token = Jwts.builder().setSubject(name).claim("roles", "user").setIssuedAt(new Date())
                .signWith(SignatureAlgorithm.HS256, "kimTestPasswordKey").compact();
        return token;
    }
}
