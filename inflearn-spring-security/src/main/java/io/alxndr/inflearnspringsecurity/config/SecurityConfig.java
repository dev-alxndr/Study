package io.alxndr.inflearnspringsecurity.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @author : Alexander Choi
 * @date : 2021/03/02
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()    // 인가
                    .mvcMatchers("/", "/info").permitAll()  // 권한 필요없음
                    .mvcMatchers("/admin").hasRole("ADMIN") // ADMIN ROLE 을 가진 사용자만 접근 가능
                    .anyRequest().authenticated();  // 그외의 접근은 권한 필요

        // 굳이 메소드 체이닝을 해야하는건 아니다.
        http.formLogin()
                .and()
                .httpBasic();
    }
}
