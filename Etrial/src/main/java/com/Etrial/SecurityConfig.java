package com.Etrial;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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
    public UserDetailsService users() {
        UserDetails admin = User.builder()
                .username("ADMIN")
                .password("{noop}123")
                .roles("USER", "VENDEDOR", "ADMIN")
                .build();
        UserDetails sales = User.builder()
                .username("VENDEDOR")
                .password("{noop}123")
                .roles("USER", "VENDEDOR")
                .build();
        UserDetails user = User.builder()
                .username("USER")
                .password("{noop}123")
                .roles("USER")
                .build();
        return new InMemoryUserDetailsManager(user, sales, admin);
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http)
            throws Exception {
        http
                .authorizeHttpRequests((requests) -> requests
                .requestMatchers(
                        "/",
                        "/index",
                        "/errores/**",
                        "/error",
                        "/webjars/**").permitAll()
                .requestMatchers(
                        "/entrada/nuevo",
                        "/entrada/guardar",
                        "/entrada/modificar/**",
                        "/entrada/eliminar/**",
                        "/evento/nuevo",
                        "/evento/guardar",
                        "/evento/modificar/**",
                        "/evento/eliminar/**",
                        "/cliente/nuevo",
                        "/cliente/guardar",
                        "/cliente/modificar/**",
                        "/cliente/eliminar/**")
                .hasRole("ADMIN")
                .requestMatchers(
                        "/entrada/listado",
                        "/evento/listado",
                        "/cliente/listado")
                .hasAnyRole("ADMIN", "VENDEDOR")
                )
                .formLogin((form) -> form
                .loginPage("/login").permitAll())
                .logout((logout) -> logout.permitAll())
                .exceptionHandling().accessDeniedPage("/errores/403");
        return http.build();
    }
}
