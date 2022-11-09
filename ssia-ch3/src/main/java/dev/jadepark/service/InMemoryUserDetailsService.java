package dev.jadepark.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;

public class InMemoryUserDetailsService implements UserDetailsService {

    private final List<UserDetails> users;  //메모리 내에서 사용자를 관리한다.

    public InMemoryUserDetailsService(List<UserDetails> users) {
        this.users = users;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return users.stream()
                .filter(    // 사용자의 목록에서 요청된 사용자 이름과 일치하는 항목을 필터링한다.
                        u -> u.getUsername().equals(username)
                )
                .findFirst()    //일치하는 사용자가 있으면 반환한다.
                .orElseThrow(   //이 사용자 이름이 존재하지 않으면 예외를 발생시킨다.
                        () -> new UsernameNotFoundException("User not found")
                );
    }
}
