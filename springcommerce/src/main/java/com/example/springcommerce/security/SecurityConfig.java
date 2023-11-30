package com.example.springcommerce.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    public static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
            http.csrf().disable()
                    .authorizeRequests(authorizeRequests ->
                            authorizeRequests
                                    .requestMatchers("/api/products/**").hasAuthority("ADMIN")
                                    .requestMatchers("/users/signup", "/users/login", "/", "/home").permitAll()
                                    .requestMatchers("/cart").authenticated()
                                    .anyRequest().permitAll()
                    )
                    .formLogin()
                    .loginPage("/users/login")
                    .defaultSuccessUrl("/home?loggedin")
                    .loginProcessingUrl("/users/login")
                    .failureUrl("/users/login?error")
                    .permitAll()
                    .and()
                    .logout()
                    .logoutUrl("/users/logout")
                    .logoutSuccessUrl("/home?loggedout")
                    .invalidateHttpSession(true)
                    .deleteCookies("JSESSIONID");

            return http.build();
        }


    @Autowired
    public void configure(AuthenticationManagerBuilder builder) throws Exception {
        builder.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }
}
