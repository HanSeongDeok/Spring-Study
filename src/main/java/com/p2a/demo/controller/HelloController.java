package com.p2a.demo.controller;

import com.p2a.demo.model.Hello;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {
    @GetMapping("/hello")
    public String helloController(Model model){
        model.addAttribute("data", "HELLO");
        return "hello";
    }

    @GetMapping("/hello-mvc")
    public String helloMvcController(@RequestParam(value = "name",required = true)String name, Model model) {
        model.addAttribute("name", name);
        return "hello-mvc";
    }

    @GetMapping("/hello-string")
    @ResponseBody
    public String helloString(@RequestParam("name")String name){
        return "hello " + name;
    }

    @GetMapping("/hello-api")
    @ResponseBody
    public Hello helloHello(@RequestParam("name")String name){
        Hello hello = new Hello(name);
        return hello;
    }
}
