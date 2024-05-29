package com.example.webapp.config;

import com.example.webapp.filter.AuthTokenFilter;
import com.example.webapp.security.AuthEntryPointJwt;
import com.example.webapp.service.JpaUserDetailsManager;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;

import java.util.Arrays;
import java.util.Collections;


@Configuration
@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true, jsr250Enabled = true, prePostEnabled = true)
public class SecurityConfig {
    private static Logger LOG = LoggerFactory.getLogger(SecurityConfig.class);

    @Autowired
    private AuthEntryPointJwt unauthorizedHandler;


    @Bean
    public AuthTokenFilter authenticationJwtTokenFilter() {
        return new AuthTokenFilter();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http,
                                           AuthenticationManager authenticationManager,
                                           HandlerMappingIntrospector introspector) throws Exception {

        MvcRequestMatcher.Builder mvcMatcherBuilder = new MvcRequestMatcher.Builder(introspector);
        AntPathRequestMatcher antPathRequestMatcher = new AntPathRequestMatcher("/h2-console/**");

        http.csrf(AbstractHttpConfigurer::disable);

        http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        http.exceptionHandling(exception -> exception.authenticationEntryPoint(unauthorizedHandler));

        http.addFilterAfter(authenticationJwtTokenFilter(), BasicAuthenticationFilter.class);
        http.addFilterAfter((request, response, filterChain) -> {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if (null != authentication) {
                LOG.info("User " + authentication.getName() + " is successfully authenticated and "
                        + "has the authorities " + authentication.getAuthorities().toString());
            }
            filterChain.doFilter(request, response);
        }, BasicAuthenticationFilter.class);

        http.headers(headersConfigurer ->
                headersConfigurer.frameOptions(HeadersConfigurer.FrameOptionsConfig::sameOrigin));

        http.authorizeHttpRequests(auth ->
                        auth
//                                .requestMatchers(mvcMatcherBuilder.pattern("/debug/**")).permitAll()
//                        .requestMatchers(mvcMatcherBuilder.pattern("/user")).hasRole("USER")
//                        .requestMatchers(mvcMatcherBuilder.pattern("/admin")).hasRole("ADMIN")
//                        .requestMatchers(mvcMatcherBuilder.pattern("/nonauth")).permitAll()
//                        .requestMatchers(mvcMatcherBuilder.pattern("/messages/**")).permitAll()
                                // .requestMatchers(mvcMatcherBuilder.pattern("/")).permitAll()
                                .requestMatchers(mvcMatcherBuilder.pattern("/demo/**")).permitAll()
                                .requestMatchers(mvcMatcherBuilder.pattern("/api/admin/users")).permitAll()
                                .requestMatchers(new AntPathRequestMatcher("/api/auth/**", "POST")).permitAll()
                                .requestMatchers(new AntPathRequestMatcher("/api/auth/**", "GET")).permitAll()
                                .requestMatchers(PathRequest.toH2Console()).permitAll()
                                .requestMatchers(antPathRequestMatcher).permitAll()
                                .anyRequest().permitAll()
        );


        return http.build();
    }


    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http, JpaUserDetailsManager userDetailsService) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
        return authenticationManagerBuilder.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**").allowedOrigins("*");
            }
        };
    }

}
