package dev.jadepark.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/hello")
    public String hello() {

        //curl -u john:12345 http://localhost:8080/hello
        //Hello!

        return "Hello!";
    }
}
