package com.example.webapp;

//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.Customizer;
//import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.provisioning.InMemoryUserDetailsManager;
//import org.springframework.security.web.SecurityFilterChain;

//@Configuration
//@EnableWebSecurity
//@EnableMethodSecurity
public class SecurityConfig {

//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        http
//                .csrf(Customizer.withDefaults())
//                .authorizeHttpRequests(authorize ->
//                        authorize
//                                .requestMatchers("/login").permitAll()
//                                .anyRequest().authenticated()
//                )
//                .httpBasic(Customizer.withDefaults())
//                .formLogin(conf -> conf.loginPage("/login"));
//        return http.build();
//    }

/*

//Default config
 @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(Customizer.withDefaults())
                .authorizeHttpRequests(authorize -> authorize
                        .anyRequest().authenticated()
                )
                .httpBasic(Customizer.withDefaults())
                .formLogin(Customizer.withDefaults());
        return http.build();
    }



@Bean
    public UserDetailsService users() {
        UserDetails user = User.builder()
                .username("user")
                .password("user")
                .roles("USER")
                .build();
        UserDetails admin = User.builder()
                .username("admin")
                .password("admin")
                .roles("USER", "ADMIN")
                .build();
        return new InMemoryUserDetailsManager(user, admin);
    }

*/

//    @Bean
//    public UserDetailsService users() {
//        // The builder will ensure the passwords are encoded before saving in memory
//        User.UserBuilder users = User.withDefaultPasswordEncoder();
//        UserDetails user = users
//                .username("user")
//                .password("password")
//                .roles("USER")
//                .build();
//        UserDetails admin = users
//                .username("admin")
//                .password("password")
//                .roles("USER", "ADMIN")
//                .build();
//        return new InMemoryUserDetailsManager(user, admin);
//    }
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
