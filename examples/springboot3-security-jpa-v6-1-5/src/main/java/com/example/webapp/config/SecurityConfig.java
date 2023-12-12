package com.example.webapp.config;

import com.example.webapp.service.JpaUserDetailsManager;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;


@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {




    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http,
                                           HandlerMappingIntrospector introspector) throws Exception {

        MvcRequestMatcher.Builder mvcMatcherBuilder = new MvcRequestMatcher.Builder(introspector);
        AntPathRequestMatcher antPathRequestMatcher = new AntPathRequestMatcher("/h2-console/**");

        http.csrf(AbstractHttpConfigurer::disable);

        http.headers(headersConfigurer ->
                headersConfigurer.frameOptions(HeadersConfigurer.FrameOptionsConfig::sameOrigin));

        http.authorizeHttpRequests(auth ->
                auth
                        .requestMatchers(mvcMatcherBuilder.pattern("/user")).hasRole("USER")
                        .requestMatchers(mvcMatcherBuilder.pattern("/admin")).hasRole("ADMIN")
                        .requestMatchers(mvcMatcherBuilder.pattern("/nonauth")).permitAll()
                        .requestMatchers(mvcMatcherBuilder.pattern("/messages/**")).permitAll()
                        //This line is optional in .authenticated() case as .anyRequest().authenticated()
                        //would be applied for H2 path anyway
                        .requestMatchers(PathRequest.toH2Console()).permitAll()
                        //.requestMatchers(antPathRequestMatcher).permitAll()
                        .anyRequest().permitAll()
        );


        http.exceptionHandling((exceptionHandling) ->
                exceptionHandling
                        .accessDeniedPage("/access-denied"));

        http.formLogin(form -> form
                .loginPage("/login")
                .permitAll());

        return http.build();
    }

    @Bean
    @Profile("!custom")
    public DaoAuthenticationProvider jpaDaoAuthenticationProvider(JpaUserDetailsManager jpaUserDetailsManager) {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(jpaUserDetailsManager);
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        return daoAuthenticationProvider;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


}
