package dev.jadepark.service;

import dev.jadepark.entity.User;
import dev.jadepark.repository.UserRepository;
import dev.jadepark.security.CustomUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.function.Supplier;

@Service
public class JpaUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public CustomUserDetails loadUserByUsername(String username) {
        //에외 인스턴스를 만들기 위한 공급자 서언
        Supplier<UsernameNotFoundException> s =
                () -> new UsernameNotFoundException("Problem during authentication!");

        User u = userRepository
                .findUserByUsername(username)   //사용자를 포함한 Optional 인스턴스를 반환하거나 사용자가 없으면 비어있는 Optional 인스턴스 반환
                .orElseThrow(s);    //Optional 인스턴스가 비어있으면 공급자가 생성한 예외투척, 그렇지 않으면 User 인스턴스 반환
        return new CustomUserDetails(u);    //User 인스턴스를 CustomUserDetails 데코레이터로 래핑하고 반환
    }
}
