package dev.jadepark;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Ch6Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        System.out.println("[프로젝트 주요 단계]");
        System.out.println("1. 데이터베이스 설정");
        System.out.println("2. 사용자 관리 정의");
        System.out.println("3. 인증 논리 구현");
        System.out.println("4. 주 페이지 구현");
        System.out.println("5. 애플리케이션 실행 및 테스트");
        SpringApplication.run(Ch6Main.class, args);

    }
}