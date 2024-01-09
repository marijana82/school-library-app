package com.marijana.library1223.configuration;

import com.marijana.library1223.filter.JwtRequestFilter;
import com.marijana.library1223.services.CustomUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfiguration {

    private final CustomUserDetailsService customUserDetailsService;
    private final JwtRequestFilter jwtRequestFilter;
    private final PasswordEncoder passwordEncoder;

    public SpringSecurityConfiguration(CustomUserDetailsService customUserDetailsService, JwtRequestFilter jwtRequestFilter, PasswordEncoder passwordEncoder) {
        this.customUserDetailsService = customUserDetailsService;
        this.jwtRequestFilter = jwtRequestFilter;
        this.passwordEncoder = passwordEncoder;
    }

    //TODO: CHECK WHICH OF THE TWO CODES IS BETTER TO USE?
    //1. this authentication manager code is from the lesson, but with this one PasswordEncoder is not in a separate configuration class
   /* @Bean
    public AuthenticationManager authenticationManager(PasswordEncoder passwordEncoder) throws Exception {
        var authentication = new DaoAuthenticationProvider();
        authentication.setPasswordEncoder(passwordEncoder);
        authentication.setUserDetailsService(customUserDetailsService);
        return new ProviderManager(authentication);
    }*/

    //2. this authentication manager code is from github, where PasswordEncoder is in a separate configuration class
   @Bean
    public AuthenticationManager authenticationManager(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity.getSharedObject(AuthenticationManagerBuilder.class)
                .userDetailsService(customUserDetailsService)
                .passwordEncoder(passwordEncoder)
                .and() //deprecated
                .build();
    }


    //authorisation with jwt
    @Bean
    protected SecurityFilterChain filter (HttpSecurity httpSecurity) throws Exception {
        //jwt token authentication
        httpSecurity
                .csrf(csrf -> csrf.disable())
                .httpBasic(basic -> basic.disable())
                .cors(Customizer.withDefaults())
                .authorizeHttpRequests(auth ->

                        auth
                                //TODO:set this line out after adding precise requestMatchers
                .requestMatchers("/**").permitAll()

                                //TODO: here create a list of all requestMatchers

                .anyRequest().permitAll() //TODO:later change this to denyAll()

                )
                .sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        httpSecurity.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
        return httpSecurity.build();

    }


}
