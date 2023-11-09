package com.example.webapp;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(Customizer.withDefaults())
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/user").hasRole("USER")
                        .requestMatchers("/admin").hasRole("ADMIN")
                        .anyRequest().authenticated()
                )
                .exceptionHandling((exceptionHandling) ->
                        exceptionHandling
                                .accessDeniedPage("/access-denied"))
                .formLogin(form -> form
                        .loginPage("/login")

                        .permitAll());

        return http.build();
    }


    @Bean
    public UserDetailsService users() {
        // The builder will ensure the passwords are encoded before saving in memory
        User.UserBuilder users = User.withDefaultPasswordEncoder();
        UserDetails user = users
                .username("user")
                .password("password")
                .roles("USER")
                .build();
        UserDetails admin = users
                .username("admin")
                .password("password")
                .roles("USER", "ADMIN")
                .build();
        return new InMemoryUserDetailsManager(user, admin);
    }
//
//    @Bean
//    public SecurityFilterChain filterChainxx(HttpSecurity http) throws Exception {
//
//        return http
//                // .csrf(csrf -> csrf.ignoringAntMatchers("/h2-console/**"))
//                .authorizeRequests(auth -> auth
////                        .requestMatchers("/h2-console/**").permitAll()
////                        .requestMatchers("/api/posts/**").permitAll()
//                                .anyRequest().authenticated()
//                )
//                //    .userDetailsService(myUserDetailsService)
//                //   .headers(headers -> headers.frameOptions().sameOrigin())
//                //  .httpBasic(withDefaults())
//                .formLogin(form -> form
//                        .loginPage("/login")
//                        .permitAll())
//                .build();
//
//        //   return http.build();
//
////        return http
////                //.csrf(csrf -> csrf.ignoringAntMatchers("/h2-console/**"))
////                .authorizeRequests(auth -> auth
////                  //      .antMatchers("/h2-console/**").permitAll()
////                        .requestMatchers("/api/posts/**").permitAll()
////                        .anyRequest().authenticated()
////                )
////              // .userDetailsService(myUserDetailsService)
////                //.headers(headers -> headers.frameOptions().sameOrigin())
////                .httpBasic(withDefaults())
////                .build();


    //   }
}
