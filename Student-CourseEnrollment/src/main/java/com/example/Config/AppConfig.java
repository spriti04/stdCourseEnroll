package com.example.Config;

import jakarta.servlet.http.HttpServletRequest;
import org.apache.tomcat.util.http.parser.Authorization;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import java.net.http.HttpRequest;
import java.util.Arrays;
import java.util.Collections;

@Configuration
public class AppConfig {


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .cors(cors ->
                        cors.configurationSource(
                                new CorsConfigurationSource() {
                                    @Override
                                    public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
                                        CorsConfiguration cfg = new CorsConfiguration();

                                        cfg.setAllowedOriginPatterns(Collections.singletonList("*"));
                                        cfg.setAllowedMethods(Collections.singletonList("*"));
                                        cfg.setAllowCredentials(true);
                                        cfg.setAllowedHeaders(Collections.singletonList("*"));
                                        cfg.setExposedHeaders(Arrays.asList("Authorization"));
                                        return cfg;
                                    }
                                }
                        ))
                .authorizeHttpRequests(auth ->
                        auth
                                .requestMatchers("/swagger-ui/**", "/v3/api-docs/**", "/admin/hello").permitAll()
                                .requestMatchers(HttpMethod.POST, "/admin/newAdmin", "/teacher/newTeacher", "/student/newStd").permitAll()
                                .requestMatchers(HttpMethod.POST, "/admin/signIn", "/teacher/signIn", "/student/signIn").permitAll()
                                .requestMatchers(HttpMethod.POST, "/admin/addCourses").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.PATCH, "/admin/updateCourse/**").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.PATCH, "/admin/updatePhNo/**").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.PATCH, "/teacher/updatePhNo/**").hasRole("TEACHER")
                                .requestMatchers(HttpMethod.PATCH, "/student/updatePhNo/**").hasRole("STUDENT")
                                .requestMatchers(HttpMethod.GET, "/admin/getUserDtl/**").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.GET, "/admin/getAllUsers").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.GET, "/admin/byCourseId/**").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.GET, "/admin/allCourses").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.GET, "/teacher/allCourses").hasRole("TEACHER")
                                .requestMatchers(HttpMethod.GET, "/student/allCourses").hasRole("STUDENT")
                                .requestMatchers(HttpMethod.DELETE, "/admin/deleteCourse/**").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.DELETE, "/admin/deleteUser/**").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.DELETE, "/teacher/deleteUser/**").hasRole("TEACHER")
                                .requestMatchers(HttpMethod.DELETE, "/student/deleteUser/**").hasRole("STUDENT")
                                .anyRequest().authenticated()
                )
                .csrf(csrf -> csrf.disable())
                .formLogin(Customizer.withDefaults())
                .httpBasic(Customizer.withDefaults())
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/signIn")
                        .deleteCookies("JSESSIONID")
                );

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
