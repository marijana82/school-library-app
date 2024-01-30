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

   @Bean
    public AuthenticationManager authenticationManager() throws Exception {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setPasswordEncoder(passwordEncoder);
        authenticationProvider.setUserDetailsService(customUserDetailsService);
        return new ProviderManager(authenticationProvider);
    }

    @Bean
    protected SecurityFilterChain filter (HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf(csrf -> csrf.disable())
                .httpBasic(basic -> basic.disable())
                .cors(Customizer.withDefaults())
                .authorizeHttpRequests(auth ->

                        auth
                                //users [xxx]
                                .requestMatchers(HttpMethod.POST, "/users/register").permitAll()
                                .requestMatchers(HttpMethod.POST, "/users/{username}/authorities").hasAuthority("ROLE_ADMIN")
                                .requestMatchers(HttpMethod.GET, "/users/{username}/authorities").hasAuthority("ROLE_ADMIN")
                                .requestMatchers(HttpMethod.DELETE, "/users/{username}/authorities/{authority}").hasAuthority("ROLE_ADMIN")
                                .requestMatchers(HttpMethod.GET, "/users").hasAnyAuthority("ROLE_ADMIN", "ROLE_LIBRARIAN")
                                .requestMatchers(HttpMethod.GET, "/users/**").hasAnyAuthority( "ROLE_ADMIN", "ROLE_LIBRARIAN")
                                .requestMatchers(HttpMethod.PUT, "/users/**").hasAuthority("ROLE_ADMIN")
                                .requestMatchers(HttpMethod.DELETE, "/users/**").hasAuthority("ROLE_ADMIN")

                                //authentication [xxx]
                                .requestMatchers(HttpMethod.POST,"/authentication/post").permitAll()
                                .requestMatchers(HttpMethod.GET,"/authentication/get").authenticated()

 
                                //accounts [xxx] -  GET ONE, PUT ONE & PATCH ONE CONTAIN @AuthenticationPrincipal
                                .requestMatchers(HttpMethod.POST, "/accounts").authenticated()
                                .requestMatchers(HttpMethod.GET, "/accounts" ).hasAuthority( "ROLE_LIBRARIAN")
                                .requestMatchers(HttpMethod.GET, "/accounts/**").hasAuthority( "ROLE_STUDENT")
                                .requestMatchers(HttpMethod.PUT, "/accounts/**").hasAuthority( "ROLE_STUDENT")
                                .requestMatchers(HttpMethod.PATCH, "/accounts/**").hasAuthority("ROLE_STUDENT")
                                .requestMatchers(HttpMethod.DELETE, "/accounts/**").hasAuthority("ROLE_ADMIN")
                                    //add user to account
                                .requestMatchers(HttpMethod.PUT, "/accounts/{idAccount}/users/{username}").hasAuthority("ROLE_LIBRARIAN")

                                //books [xxx]
                                .requestMatchers(HttpMethod.POST, "/books").hasAuthority("ROLE_ADMIN")
                                .requestMatchers(HttpMethod.GET, "/books/**").permitAll()
                                .requestMatchers(HttpMethod.GET, "/books/reviews/**").permitAll()
                                .requestMatchers(HttpMethod.PUT, "/books/**").hasAuthority("ROLE_LIBRARIAN")
                                .requestMatchers(HttpMethod.PATCH, "/books/**").hasAuthority("ROLE_LIBRARIAN")
                                .requestMatchers(HttpMethod.DELETE, "/books/**").hasAuthority("ROLE_ADMIN")
                                    //add photo to book
                                .requestMatchers(HttpMethod.PUT, "/books/{idBook}/photo/**").hasAnyAuthority("ROLE_ADMIN", "ROLE_LIBRARIAN")

                                //book-copies [xxx]
                                .requestMatchers(HttpMethod.POST, "/book-copy").hasAuthority("ROLE_LIBRARIAN")
                                .requestMatchers(HttpMethod.GET, "/book-copy/**").hasAuthority("ROLE_LIBRARIAN")
                                .requestMatchers(HttpMethod.PUT, "/book-copy/**").hasAuthority("ROLE_LIBRARIAN")
                                .requestMatchers(HttpMethod.DELETE, "/book-copy/**").hasAuthority("ROLE_LIBRARIAN")
                                    //add book to copy
                                .requestMatchers(HttpMethod.PUT, "/book-copy/{idCopy}/books/{idBook}").hasAuthority("ROLE_LIBRARIAN")

                                //borrowals [xxx]
                                .requestMatchers(HttpMethod.POST, "/borrowals").hasAuthority("ROLE_LIBRARIAN")
                                .requestMatchers(HttpMethod.GET, "/borrowals/**").hasAuthority("ROLE_LIBRARIAN")
                                .requestMatchers(HttpMethod.PUT, "/borrowals/{idBorrowal}").hasAuthority("ROLE_LIBRARIAN")
                                .requestMatchers(HttpMethod.PATCH, "/borrowals/{idBorrowal}").hasAuthority("ROLE_LIBRARIAN")
                                .requestMatchers(HttpMethod.DELETE, "/borrowals/**").hasAuthority("ROLE_LIBRARIAN")
                                    //add book-copy, reservation, and account to borrowal:
                                .requestMatchers(HttpMethod.PUT, "/borrowals/{idBorrowal}/copies/{idCopy}").hasAuthority("ROLE_LIBRARIAN")
                                .requestMatchers(HttpMethod.PUT, "/borrowals/{idBorrowal}/reservations/{idReservation}").hasAuthority("ROLE_LIBRARIAN")
                                .requestMatchers(HttpMethod.PUT, "/borrowals/{idBorrowal}/accounts/{idAccount}").hasAuthority("ROLE_LIBRARIAN")


                                //reservations [x]
                                .requestMatchers(HttpMethod.POST, "/reservations").hasAuthority("ROLE_STUDENT")
                                .requestMatchers(HttpMethod.GET, "/reservations").hasAuthority("ROLE_LIBRARIAN")
                                .requestMatchers(HttpMethod.GET, "/reservations/dates").hasAuthority("ROLE_LIBRARIAN")
                                .requestMatchers(HttpMethod.GET, "/reservations/**").hasAuthority("ROLE_STUDENT")
                                .requestMatchers(HttpMethod.PUT, "/reservations/{idReservation}").hasAuthority("ROLE_STUDENT")
                                .requestMatchers(HttpMethod.DELETE, "/reservations/**").hasAuthority("ROLE_STUDENT")
                                    //add book to reservation
                                .requestMatchers(HttpMethod.PUT, "/reservations/{idReservation}/books/{idBook}").hasAuthority("ROLE_STUDENT")
                                    //add account to reservation
                                .requestMatchers(HttpMethod.PUT, "/reservations/{idReservation}/accounts/{idAccount}").hasAuthority("ROLE_STUDENT")

 
                                //file upload [xxx]
                                .requestMatchers(HttpMethod.POST, "/single/upload").hasAnyAuthority("ROLE_LIBRARIAN", "ROLE_ADMIN")
                                .requestMatchers(HttpMethod.GET, "/download/allNames").hasAuthority("ROLE_LIBRARIAN")
                                .requestMatchers(HttpMethod.GET, "/download").permitAll()


                                //book-reviews [x]
                                .requestMatchers(HttpMethod.POST, "/add-book-to-review/**").authenticated() //only authenticated users can post a book review

                                //all other requests not defined above
                                .anyRequest().permitAll()

                )

                .sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        httpSecurity.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
        return httpSecurity.build();

    }


}
