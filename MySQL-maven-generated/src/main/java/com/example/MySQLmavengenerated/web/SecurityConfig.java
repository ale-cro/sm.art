package com.example.MySQLmavengenerated.web;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
/*  The preceding configuration automatically registers an in-memory UserDetailsService with the SecurityFilterChain,
    registers the DaoAuthenticationProvider with the default AuthenticationManager,
    and enables Form Login and HTTP Basic authentication.
    The WebSecurityConfig class is annotated with @EnableWebSecurity to enable Spring Security’s web security support and provide the Spring MVC integration.
    The SecurityFilterChain bean defines which URL paths should be secured and which should not.
    In Spring Security 5.7.0-M2 we deprecated the WebSecurityConfigurerAdapter, as we encourage users to move towards a component-based security configuration.
    Going forward, the recommended way of doing this is registering a SecurityFilterChain bean:
    https://spring.io/blog/2022/02/21/spring-security-without-the-websecurityconfigureradapter/ */

/**
 * Classe dedicata alla profilazione Utenti
 * Nota: Al momento vengono accettate tutte le richieste, senza richiedere autenticazione.
 * Da configurare nel caso in cui si vuole abilitare sicurezza per attacchi CSRF
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    /*Basically this tells Spring to allow access to every url. @Configuration tells spring it's a configuration class
     * https://stackoverflow.com/questions/36280181/disabling-spring-security-in-spring-boot-app*/
    /*In this segment of code, we configure the application to permit all incoming requests without requiring any form of authentication.
    * Per Testing, al momento vengono accettate tutte le richieste, senza eseguire autenticazione */
    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeRequests(authorizeRequests -> authorizeRequests.anyRequest()
                        .permitAll())
                .csrf(AbstractHttpConfigurer::disable); //per disabilitare sicurezza csrf (finestra di login di Spring security)
        return http.build();
    }

    //A seguire questi Beans per definire a chi assegnare l'accesso
    /*
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((authorize) -> authorize
                        .anyRequest().authenticated()
                )
                .httpBasic(Customizer.withDefaults())
                .formLogin(Customizer.withDefaults());

        return http.build();
    }*/

    /*next, let’s configure Spring Security by adding an in-memory user:*/
    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails userDetails = User.withDefaultPasswordEncoder()
                .username("user")
                .password("test")
                .roles("USER")
                .build();
        return new InMemoryUserDetailsManager(userDetails);
    }
}
