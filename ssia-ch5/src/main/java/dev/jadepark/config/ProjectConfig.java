package dev.jadepark.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class ProjectConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler;
    @Autowired
    private CustomAuthenticationFailureHandler customAuthenticationFailureHandler;


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        /*
        http.httpBasic(c -> {
            c.realmName("OTHER");
            c.authenticationEntryPoint(new CustomEntryPoint()); //인증 실패시 헤더에 message 추가
        });
        */


        /*
        http.formLogin()
                .defaultSuccessUrl("/home", true);
        */
        //세부적인 맞춤구성이 필요하다면 AuthenticationSuccessHandler 및 AuthenticationFailureHandler를 사용할 것

        http.formLogin().successHandler(customAuthenticationSuccessHandler)
                        .failureHandler(customAuthenticationFailureHandler)
                .and()
                .httpBasic();

        http.authorizeRequests().anyRequest().authenticated();
    }


}
