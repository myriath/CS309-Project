package com.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Security config to enable HTTPS and disable CORS
 * EXPERIMENT 5
 *
 * @author Mitch Hudson
 */
@Configuration
public class SecurityConfig {

//    /**
//     * This filters out requests, requiring HTTPS and disabling CORS and CSRF.
//     * TODO: Stop disabling CORS and CSRF.
//     * @param http          HTTP request
//     * @return              Built HTTP request
//     * @throws Exception    Errors
//     */
//    @Bean
//    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        return http.httpBasic().disable().formLogin().disable()
//                .requiresChannel(channel ->
//                        channel.anyRequest().requiresSecure())
//                .authorizeRequests(authorize ->
//                        authorize.anyRequest().permitAll())
//                .build();
//    }

}
