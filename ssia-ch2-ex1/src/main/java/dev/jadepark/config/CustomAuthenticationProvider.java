package dev.jadepark.config;

import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import java.lang.reflect.Array;
import java.util.Arrays;

@Component  //Bean등록
public class CustomAuthenticationProvider implements AuthenticationProvider {

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        //인증 논리 추가
        String username = authentication.getName();  //Principal 인터페이스의 getName() 메서드를 상속받는다.
        String password = String.valueOf(authentication.getCredentials());

        // UserDetailsService와 PasswordEncoder를 대체하는 조건
        if("john".equals(username) && "12345".equals(password)) {
            return new UsernamePasswordAuthenticationToken(username, password, Arrays.asList());
        }else {
            throw new AuthenticationCredentialsNotFoundException("Error in authentication!");
        }

    }

    @Override
    public boolean supports(Class<?> authenticationType) {

        //Authentication 형식의 구현을 추가
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authenticationType);
    }
}
