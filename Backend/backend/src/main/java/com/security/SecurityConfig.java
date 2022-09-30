package com.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.List;

/**
 * Security config to enable HTTPS and disable CORS
 * EXPERIMENT 5
 *
 * @author Mitch Hudson
 */
@Configuration
public class SecurityConfig {

    /**
     * This filters out requests, requiring HTTPS and disabling CORS and CSRF.
     * TODO: Stop disabling CORS and CSRF.
     * @param http          HTTP request
     * @return              Built HTTP request
     * @throws Exception    Errors
     */
    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http.cors().and().csrf().disable()
                .requiresChannel(channel ->
                        channel.anyRequest().requiresSecure())
                .authorizeRequests(authorize ->
                        authorize.anyRequest().permitAll())
                .build();
//        http.authorizeRequests()
//                .antMatchers(HttpMethod.GET, "/**").permitAll()
//                .anyRequest().authenticated();

//        return http.build();
    }

    /**
     * This allows requests from all sources.
     * TODO: Remove or change to fit our needs, allowing all is INSECURE
     *
     * @return  Configured source
     */
    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(List.of("*"));
        configuration.setAllowedMethods(List.of("*"));
        configuration.setAllowedHeaders(List.of("*"));
        configuration.setAllowCredentials(true);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

}
