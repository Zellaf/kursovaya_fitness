package com.fitness.config;

import com.fitness.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    @Autowired
    private UserService userService;

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeHttpRequests()

                // all
                .requestMatchers("/registration").permitAll()
                .requestMatchers("/").permitAll()

                // auth
                .requestMatchers("/coach").authenticated()
                .requestMatchers("/subscription").authenticated()
                .requestMatchers("/user-subscription").authenticated()

                // only admin
                .requestMatchers("/history/**").hasRole("ADMIN")

                // users and admin edit info
                .requestMatchers("/user/**").hasRole("ADMIN")
                .requestMatchers("/coach/**").hasRole("ADMIN")
                .requestMatchers("/subscription/**").hasRole("ADMIN")
                .requestMatchers("/user-subscription/**").hasRole("ADMIN")

                .requestMatchers("/resources/**").permitAll()
                .requestMatchers("/css/**").permitAll()
                .requestMatchers("/img/**").permitAll()

                // deny all
                .requestMatchers("/explorer/**").denyAll()
                .requestMatchers("/profile/**").denyAll()
                .requestMatchers("/actuator/**").denyAll()

                .anyRequest().authenticated()

                .and()
                .formLogin().loginPage("/login").defaultSuccessUrl("/").permitAll()

                .and()
                .logout().permitAll().logoutSuccessUrl("/");

        return http.build();
    }

    @Autowired
    protected void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService).passwordEncoder(bCryptPasswordEncoder());
    }

}
