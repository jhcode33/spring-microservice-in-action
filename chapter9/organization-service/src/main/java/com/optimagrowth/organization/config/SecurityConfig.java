package com.optimagrowth.organization.config;

import com.optimagrowth.organization.jwt.JwtAuthConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
//@EnableGlobalMethodSecurity(jsr250Enabled = true) => deprecated
public class SecurityConfig {

    public static final String ADMIN = "admin";
    public static final String USER = "user";
    private final JwtAuthConverter jwtAuthConverter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.csrf(csrf -> csrf.disable())

                .authorizeHttpRequests(authorize -> authorize
                        .anyRequest().authenticated())

                .oauth2ResourceServer(oauth -> oauth
                        .jwt(jwt -> jwt
                                .jwtAuthenticationConverter(jwtAuthConverter)))
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .build();
    }

//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        super.configure(http);
//        http.authorizeRequests()
//            .anyRequest().authenticated();
//        http.csrf().disable();
//    }

//    @Autowired
//    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//        KeycloakAuthenticationProvider keycloakAuthenticationProvider = keycloakAuthenticationProvider();
//        keycloakAuthenticationProvider.setGrantedAuthoritiesMapper(new SimpleAuthorityMapper());
//        auth.authenticationProvider(keycloakAuthenticationProvider);
//    }

//    @Bean
//    @Override
//    protected SessionAuthenticationStrategy sessionAuthenticationStrategy() {
//        return new RegisterSessionAuthenticationStrategy(new SessionRegistryImpl());
//    }

//    @Bean
//    public KeycloakConfigResolver KeycloakConfigResolver() {
//        return new KeycloakSpringBootConfigResolver();
//    }
}
