package com.example.webapp.config;

import com.example.webapp.filter.JwtGeneratorFilter;
import com.example.webapp.filter.JwtValidationFilter;
import com.example.webapp.service.JpaUserDetailsManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;


@Configuration
@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true, jsr250Enabled = true, prePostEnabled = true)
public class SecurityConfig {
    private static final Logger LOG = LoggerFactory.getLogger(SecurityConfig.class);


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http,
                                           HandlerMappingIntrospector introspector) throws Exception {

        MvcRequestMatcher.Builder mvcMatcherBuilder = new MvcRequestMatcher.Builder(introspector);
        AntPathRequestMatcher antPathRequestMatcher = new AntPathRequestMatcher("/h2-console/**");

        http.csrf(AbstractHttpConfigurer::disable);

//        http.addFilterAfter(new JwtGeneratorFilter(), BasicAuthenticationFilter.class)
        http.addFilterBefore(new JwtValidationFilter(), BasicAuthenticationFilter.class);

//        http.addFilterAfter((request, response, filterChain) -> {
//
//            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//            if (null != authentication) {
//                LOG.info("User " + authentication.getName() + " is successfully authenticated and "
//                        + "has the authorities " + authentication.getAuthorities().toString());
//            }
//
//            filterChain.doFilter(request, response);
//        }, BasicAuthenticationFilter.class).headers(headersConfigurer ->
//                headersConfigurer.frameOptions(HeadersConfigurer.FrameOptionsConfig::sameOrigin));

        http.authorizeHttpRequests(auth ->
                auth
                        .requestMatchers(mvcMatcherBuilder.pattern("/user")).hasRole("USER")
                        .requestMatchers(mvcMatcherBuilder.pattern("/admin")).hasRole("ADMIN")
                        .requestMatchers(mvcMatcherBuilder.pattern("/nonauth")).permitAll()
                        .requestMatchers(mvcMatcherBuilder.pattern("/login")).permitAll()
                        // mvc rest
                        .requestMatchers(mvcMatcherBuilder.pattern("/rest/**")).permitAll()

                        //JWT + REST
                        .requestMatchers(mvcMatcherBuilder.pattern("/jwt/**")).permitAll()

                        .anyRequest().authenticated()
        );


        http.exceptionHandling((exceptionHandling) ->
                exceptionHandling
                        .accessDeniedPage("/access-denied"));

        http.formLogin(form -> form
                .loginPage("/login")
                .permitAll());

        http.httpBasic(Customizer.withDefaults());

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
