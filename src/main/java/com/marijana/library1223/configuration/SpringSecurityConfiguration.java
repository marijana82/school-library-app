package com.marijana.library1223.configuration;

import com.marijana.library1223.filter.JwtRequestFilter;
import com.marijana.library1223.services.CustomUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
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
   @Bean
    public AuthenticationManager authenticationManager() throws Exception {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setPasswordEncoder(passwordEncoder);
        authenticationProvider.setUserDetailsService(customUserDetailsService);
        return new ProviderManager(authenticationProvider);
    }

  /* @Bean
    public AuthenticationManager authenticationManager(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity.getSharedObject(AuthenticationManagerBuilder.class)
                .userDetailsService(customUserDetailsService)
                .passwordEncoder(passwordEncoder)
                .and() //deprecated
                .build();
    }*/

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
                                //TODO:set this line OUT after adding precise requestMatchers
                                .requestMatchers("/**").permitAll()

                                //TODO: here create a list of all requestMatchers
                                //---------------
                                //for users [x]
                                .requestMatchers(HttpMethod.POST, "/users/register").permitAll()  //also non registered users can create account
                                .requestMatchers(HttpMethod.PUT, "/users/**").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.PATCH, "/users/**").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.DELETE, "/users/**").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.GET, "/users").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.GET, "/users/**").hasAnyRole("ADMIN", "LIBRARIAN")

                                //for authentication []
                                .requestMatchers("/authentication/post").permitAll()
                                .requestMatchers("/authentication/get").authenticated()

                                //for accounts []
                                .requestMatchers(HttpMethod.POST, "/accounts").authenticated() //to create an account a user has to be authenticated
                                .requestMatchers(HttpMethod.GET, "/accounts" ).hasRole("LIBRARIAN")
                                .requestMatchers(HttpMethod.GET, "/accounts/**").hasAnyRole("LIBRARIAN", "STUDENT")
                                .requestMatchers(HttpMethod.PUT, "/accounts/**").hasAnyRole( "LIBRARIAN", "STUDENT")
                                .requestMatchers(HttpMethod.PATCH, "/accounts/**").hasAnyRole("LIBRARIAN", "STUDENT")
                                .requestMatchers(HttpMethod.DELETE, "/accounts/{idAccount}").hasRole("LIBRARIAN")

                                //for books []
                                .requestMatchers(HttpMethod.POST, "/books").hasAnyRole("ADMIN","LIBRARIAN")
                                .requestMatchers(HttpMethod.GET, "/books/reviews/{idBook}").permitAll() //anybody can read a review for a specific book
                                .requestMatchers(HttpMethod.GET, "/books/**").hasAnyRole("LIBRARIAN", "STUDENT")
                                .requestMatchers(HttpMethod.PUT, "/books/**").hasRole("LIBRARIAN")
                                .requestMatchers(HttpMethod.PATCH, "/books/**").hasRole("LIBRARIAN")
                                .requestMatchers(HttpMethod.DELETE, "/books/**").hasRole("ADMIN")

                                //for book copies []
                                .requestMatchers(HttpMethod.POST, "/book-copy").hasAnyRole("ADMIN", "LIBRARIAN")
                                .requestMatchers(HttpMethod.GET, "/book-copy/**").hasAnyRole("ADMIN", "LIBRARIAN")
                                .requestMatchers(HttpMethod.PUT, "/book-copy/**").hasRole("LIBRARIAN")
                                .requestMatchers(HttpMethod.PATCH, "/book-copy/**").hasRole("LIBRARIAN")
                                .requestMatchers(HttpMethod.DELETE, "/book-copy/{idCopy}").hasRole("ADMIN")

                                //for borrowals []
                                .requestMatchers(HttpMethod.POST, "/borrowals").hasRole("LIBRARIAN")
                                .requestMatchers(HttpMethod.GET, "/borrowals/**").hasRole("LIBRARIAN")
                                .requestMatchers(HttpMethod.PUT, "/borrowals/**").hasRole("LIBRARIAN")
                                .requestMatchers(HttpMethod.PATCH, "/borrowals/**").hasRole("LIBRARIAN")
                                .requestMatchers(HttpMethod.DELETE, "/borrowals/**").hasRole("LIBRARIAN")

                                //for reservations []
                                .requestMatchers(HttpMethod.POST, "/reservations").hasAnyRole("LIBRARIAN")
                                .requestMatchers(HttpMethod.GET, "/reservations/dates").hasRole("LIBRARIAN")
                                .requestMatchers(HttpMethod.GET, "/reservations/{idReservation}").hasAnyRole("LIBRARIAN", "STUDENT")
                                .requestMatchers(HttpMethod.GET, "/reservations").hasRole("LIBRARIAN")
                                .requestMatchers(HttpMethod.PUT, "/reservations/**").hasAnyRole("LIBRARIAN")
                                .requestMatchers(HttpMethod.DELETE, "/reservations/**").hasAnyRole("LIBRARIAN")

                                //for file upload []
                                .requestMatchers(HttpMethod.POST, "/single/upload").hasRole("LIBRARIAN")
                                .requestMatchers(HttpMethod.GET, "/download/allNames").hasAnyRole("LIBRARIAN")
                                .requestMatchers(HttpMethod.GET, "/download/one/").permitAll()  //anybody can download a file

                                //for book reviews []
                                .requestMatchers(HttpMethod.POST, "/reviews-books/**").authenticated() //only authenticated users can post a book review
                                .requestMatchers(HttpMethod.GET, "/reviews/**").permitAll() //anybody can read a book review

                                //all other requests not defined above
                                .anyRequest().permitAll() //TODO:later change this to denyAll()

                )

                .sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        httpSecurity.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
        return httpSecurity.build();

    }


}
