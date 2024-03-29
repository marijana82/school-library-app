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
                                //users
                                .requestMatchers(HttpMethod.POST, "/users/register").permitAll()
                                .requestMatchers(HttpMethod.POST, "/users/{username}/authorities").hasAuthority("ROLE_ADMIN")
                                .requestMatchers(HttpMethod.GET, "/users/{username}/authorities").hasAuthority("ROLE_ADMIN")
                                .requestMatchers(HttpMethod.DELETE, "/users/{username}/authorities/{authority}").hasAuthority("ROLE_ADMIN")
                                .requestMatchers(HttpMethod.GET, "/users").hasAnyAuthority("ROLE_ADMIN")
                                .requestMatchers(HttpMethod.GET, "/users/**").hasAuthority( "ROLE_STUDENT")
                                .requestMatchers(HttpMethod.PUT, "/users/**").hasAnyAuthority("ROLE_STUDENT")
                                .requestMatchers(HttpMethod.DELETE, "/users/**").hasAnyAuthority("ROLE_STUDENT", "ROLE_ADMIN")

                                //authentication
                                .requestMatchers(HttpMethod.POST,"/authentication/post").permitAll()
                                .requestMatchers(HttpMethod.GET,"/authentication/get").authenticated()

                                //accounts
                                .requestMatchers(HttpMethod.POST, "/accounts").authenticated()
                                .requestMatchers(HttpMethod.GET, "/accounts" ).hasAuthority( "ROLE_LIBRARIAN")
                                .requestMatchers(HttpMethod.GET, "/accounts/**").hasAnyAuthority( "ROLE_LIBRARIAN", "ROLE_STUDENT")
                                .requestMatchers(HttpMethod.PUT, "/accounts/**").hasAuthority( "ROLE_STUDENT")
                                .requestMatchers(HttpMethod.PATCH, "/accounts/**").hasAuthority("ROLE_STUDENT")
                                .requestMatchers(HttpMethod.DELETE, "/accounts/**").hasAnyAuthority("ROLE_LIBRARIAN","ROLE_STUDENT")
                                .requestMatchers(HttpMethod.PUT, "/accounts/{idAccount}/users/{username}").hasAnyAuthority("ROLE_LIBRARIAN", "ROLE_STUDENT")

                                //books
                                .requestMatchers(HttpMethod.POST, "/books").hasAuthority("ROLE_ADMIN")
                                .requestMatchers(HttpMethod.GET, "/books/**").permitAll()
                                .requestMatchers(HttpMethod.PUT, "/books/**").hasAnyAuthority("ROLE_ADMIN","ROLE_LIBRARIAN")
                                .requestMatchers(HttpMethod.PATCH, "/books/**").hasAnyAuthority("ROLE_ADMIN","ROLE_LIBRARIAN")
                                .requestMatchers(HttpMethod.DELETE, "/books/**").hasAuthority("ROLE_ADMIN")
                                .requestMatchers(HttpMethod.PUT, "/books/{idBook}/photo/**").hasAnyAuthority("ROLE_ADMIN", "ROLE_LIBRARIAN")

                                //book-copies
                                .requestMatchers(HttpMethod.POST, "/book-copy").hasAuthority("ROLE_LIBRARIAN")
                                .requestMatchers(HttpMethod.GET, "/book-copy/**").hasAuthority("ROLE_LIBRARIAN")
                                .requestMatchers(HttpMethod.PUT, "/book-copy/**").hasAuthority("ROLE_LIBRARIAN")
                                .requestMatchers(HttpMethod.DELETE, "/book-copy/**").hasAuthority("ROLE_LIBRARIAN")
                                .requestMatchers(HttpMethod.PUT, "/book-copy/{idCopy}/books/{idBook}").hasAuthority("ROLE_LIBRARIAN")

                                //borrowals
                                .requestMatchers(HttpMethod.POST, "/borrowals").hasAuthority("ROLE_LIBRARIAN")
                                .requestMatchers(HttpMethod.GET, "/borrowals/**").hasAuthority("ROLE_LIBRARIAN")
                                .requestMatchers(HttpMethod.PUT, "/borrowals/{idBorrowal}").hasAuthority("ROLE_LIBRARIAN")
                                .requestMatchers(HttpMethod.PATCH, "/borrowals/{idBorrowal}").hasAuthority("ROLE_LIBRARIAN")
                                .requestMatchers(HttpMethod.DELETE, "/borrowals/**").hasAuthority("ROLE_LIBRARIAN")
                                .requestMatchers(HttpMethod.PUT, "/borrowals/{idBorrowal}/copies/{idCopy}").hasAuthority("ROLE_LIBRARIAN")
                                .requestMatchers(HttpMethod.PUT, "/borrowals/{idBorrowal}/accounts/{idAccount}").hasAuthority("ROLE_LIBRARIAN")

                                //reservations
                                .requestMatchers(HttpMethod.POST, "/reservations").hasAuthority("ROLE_STUDENT")
                                .requestMatchers(HttpMethod.GET, "/reservations").hasAnyAuthority("ROLE_LIBRARIAN", "ROLE_STUDENT")
                                .requestMatchers(HttpMethod.GET, "/reservations/dates").hasAnyAuthority("ROLE_LIBRARIAN", "ROLE_STUDENT")
                                .requestMatchers(HttpMethod.GET, "/reservations/**").hasAnyAuthority("ROLE_LIBRARIAN","ROLE_STUDENT")
                                .requestMatchers(HttpMethod.PUT, "/reservations/{idReservation}").hasAuthority("ROLE_STUDENT")
                                .requestMatchers(HttpMethod.DELETE, "/reservations/**").hasAuthority("ROLE_STUDENT")
                                .requestMatchers(HttpMethod.PUT, "/reservations/{idReservation}/books/{idBook}").hasAuthority("ROLE_STUDENT")
                                .requestMatchers(HttpMethod.PUT, "/reservations/{idReservation}/accounts/{idAccount}").hasAuthority("ROLE_STUDENT")
 
                                //file upload
                                .requestMatchers(HttpMethod.POST, "/single/upload").hasAnyAuthority("ROLE_LIBRARIAN", "ROLE_ADMIN")
                                .requestMatchers(HttpMethod.GET, "/download/allNames").hasAuthority("ROLE_LIBRARIAN")
                                .requestMatchers(HttpMethod.GET, "/download").permitAll()

                                //book-reviews
                                .requestMatchers(HttpMethod.POST, "/reviews").authenticated()
                                .requestMatchers(HttpMethod.PUT, "/reviews/{idReviews}/{idBooks}").authenticated()
                                .requestMatchers(HttpMethod.GET, "/books/reviews/**").permitAll()
                                .requestMatchers(HttpMethod.GET, "/reviews/**").permitAll()

                                //all other requests not defined above
                                .anyRequest().permitAll()

                )

                .sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        httpSecurity.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
        return httpSecurity.build();

    }


}
