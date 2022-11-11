package dev.jadepark.controller;

import org.springframework.scheduling.annotation.Async;
import org.springframework.security.concurrent.DelegatingSecurityContextCallable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@RestController
public class HelloController {

    @GetMapping("/hello")
    public String hello() {

        //curl -u john:12345 http://localhost:8080/hello
        //Hello!

        SecurityContext context = SecurityContextHolder.getContext();
        System.out.println("context="+context);
        //context=org.springframework.security.core.context.SecurityContextImpl@f90ce06e: Authentication: org.springframework.security.authentication.UsernamePasswordAuthenticationToken@f90ce06e: Principal: org.springframework.security.core.userdetails.User@31dd0b: Username: john; Password: [PROTECTED]; Enabled: true; AccountNonExpired: true; credentialsNonExpired: true; AccountNonLocked: true; Granted Authorities: write; Credentials: [PROTECTED]; Authenticated: true; Details: org.springframework.security.web.authentication.WebAuthenticationDetails@957e: RemoteIpAddress: 127.0.0.1; SessionId: null; Granted Authorities: write

        Authentication a = context.getAuthentication();
        System.out.println("Authentication="+a);
        //Authentication=org.springframework.security.authentication.UsernamePasswordAuthenticationToken@f90ce06e: Principal: org.springframework.security.core.userdetails.User@31dd0b: Username: john; Password: [PROTECTED]; Enabled: true; AccountNonExpired: true; credentialsNonExpired: true; AccountNonLocked: true; Granted Authorities: write; Credentials: [PROTECTED]; Authenticated: true; Details: org.springframework.security.web.authentication.WebAuthenticationDetails@957e: RemoteIpAddress: 127.0.0.1; SessionId: null; Granted Authorities: write

        return "Hello, " + a.getName();
    }

    @GetMapping("/hello2")
    public String hello2(Authentication a) {    //스프링부트가 현재 Authentication을 메서드 매개변수로 전달한다.

        bye();

        return "Hello, " + a.getName();
    }

    @GetMapping("/bye")
    @Async
    public String bye() {
        SecurityContext context = SecurityContextHolder.getContext();
        String username = context.getAuthentication().getName();
        System.out.println("username=" + username);

        // 비동기 호출의 경후 보안컨텍스트 관리 전략에 의해서 NPE가 발생한다.
        // 이유는 보안 컨텍스트를 상속하지 않은 다른 스레드에서 실행되기 때문이다. 이를 해결하려면 MODE_INHERITABLETHREADLOCAL 전략을 사용해야 한다.

        return "bye, " + username;
    }

    @GetMapping("/ciao")
    public String ciao() throws Exception{
        Callable<String> task = () -> {
            SecurityContext context = SecurityContextHolder.getContext();
            return context.getAuthentication().getName();
        };
        ExecutorService e = Executors.newCachedThreadPool();
        try {
            var contextTask = new DelegatingSecurityContextCallable<>(task);
            return "Ciao, " + e.submit(contextTask).get() + "!";
        }finally {
            e.shutdown();
        }
    }
}
