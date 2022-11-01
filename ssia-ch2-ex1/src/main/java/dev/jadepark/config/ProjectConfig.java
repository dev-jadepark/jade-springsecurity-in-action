package dev.jadepark.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;


@Configuration
public class ProjectConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private CustomAuthenticationProvider customAuthenticationProvider;


// (1) @Bean 어노테이션을 이용하는 방법
//    @Bean   //@Bean 어노테이션은 반환된 값을 스프링 컨텍스트에 빈으로 추가하도록 스프링에 지시
//    public UserDetailsService userDetailsService() {
//        /*
//        var
//        java10에 도입된 예약 형식 이름, 로컬 선언에만 이용
//         */
//
//        var userDetailsService = new InMemoryUserDetailsManager();
//
//        var user = User.withUsername("jade")
//                .password("12345")
//                .authorities("read")
//                .build();
//
//        userDetailsService.createUser(user);
//
//
//
//        return userDetailsService;
//    }
//
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//
//        /*
//        NoOpPasswordEncoder
//        암호에 암호화나 해시를 적용하지 않고 일반 텍스트를 String equals() 메서드로 비교
//         */
//
//        return NoOpPasswordEncoder.getInstance();   //싱글턴
//
//    }


    // (2) override 메서드 사용방법
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

//        var userDetailsService = new InMemoryUserDetailsManager();
//
//        var user = User.withUsername("jade")
//                .password("12345")
//                .authorities("read")
//                .build();
//
//        userDetailsService.createUser(user);
//
//        auth.userDetailsService(userDetailsService).passwordEncoder(NoOpPasswordEncoder.getInstance());

        //커스텀 인증 논리를 등록할 수 있다.
        auth.authenticationProvider(customAuthenticationProvider);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        http.httpBasic();
//        http.authorizeRequests()
//                .anyRequest().authenticated();  //모든 요청에 인증이 필요하도록 설정

        http.httpBasic();
        http.authorizeRequests()
                .anyRequest().permitAll();  //모든 요청에 인증없이 설정
    }
}
