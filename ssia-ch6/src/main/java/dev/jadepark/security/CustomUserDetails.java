package dev.jadepark.security;

import dev.jadepark.entity.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.stream.Collectors;

public class CustomUserDetails implements UserDetails {

    private final User user;

    public CustomUserDetails(User user) {
        this.user = user;   //User 형식의 인스턴스를 래핑하고 반환한다.
    }

    public final User getUser() {
        return user;    //User Entity를 래핑
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return user.getAuthorities().stream().map(a -> new SimpleGrantedAuthority(a.getName())) //데이터베이스에서 각 사용자에대해 발견된 권한이름을 SimpleGrantedAuthority로 래핑
                .collect(Collectors.toList());  //모든 인스턴스를 목록으로 수집하고 반환
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
