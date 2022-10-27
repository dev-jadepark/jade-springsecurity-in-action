package dev.jadepark.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/hello")
    public String hello() {
        System.out.println("hello");
        return "hello";
    }

    /*
    Last login: Fri Oct  7 13:56:20 on ttys003
    % curl http://localhost:8080/hello
    {"timestamp":"2022-10-09T06:36:09.579+0000","status":401,"error":"Unauthorized","message":"Unauthorized","path":"/hello"}%

    curl -u user:bd9ba8e9-c802-4c60-9bbc-1ccfecb49052 http://localhost:8080/hello
    hello%
    jhp@JHui-MacBookPro ~ % echo -n user:bd9ba8e9-c802-4c60-9bbc-1ccfecb49052 | base64
    dXNlcjpiZDliYThlOS1jODAyLTRjNjAtOWJiYy0xY2NmZWNiNDkwNTI=
    curl -H "Authorization: Basic dXNlcjpiZDliYThlOS1jODAyLTRjNjAtOWJiYy0xY2NmZWNiNDkwNTI=" http://localhost:8080/hello
    hello%
     */
}
