package dev.jadepark.config;

import org.springframework.security.crypto.password.PasswordEncoder;

public class PlainTextPasswordEncoder implements PasswordEncoder {

    @Override
    public String encode(CharSequence rawPassword) {
        return rawPassword.toString();  //암호를 변경하지 않고 그대로 반환한다
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        return rawPassword.equals(encodedPassword); //두 문자열이 같은지를 확인
    }

    @Override
    public boolean upgradeEncoding(String encodedPassword) {
        return false;   //이중 인코딩 사용안함
    }
}
