package com.test.tripproject.security;

import com.test.tripproject.repositories.UserDao;
import com.test.tripproject.repositories.impls.UserDaoImpl;
import com.test.tripproject.security.services.CustomUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    private final UserDao userDao;

    public SecurityConfig(UserDaoImpl userDao){
        this.userDao = userDao;
    }

    @Bean
    public SecurityFilterChain securityFilterChain1(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(requests ->
                        requests
                                .requestMatchers(HttpMethod.POST, "/user/register").permitAll()
                                .requestMatchers(HttpMethod.GET, "/user/getCsrfToken").hasRole("ADMIN")
                                .anyRequest().authenticated()
                        )
                .sessionManagement(session ->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                        )
                .httpBasic(withDefaults());


        return http.build();

    }

    @Bean
    public UserDetailsService userDetailsService(){
        return new CustomUserDetailsService((UserDaoImpl) userDao);
    }

}
