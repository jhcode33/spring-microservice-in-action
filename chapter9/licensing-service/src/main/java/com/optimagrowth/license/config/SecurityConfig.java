package com.optimagrowth.license.config;

import com.optimagrowth.license.jwt.JwtAuthConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

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
		return http
				.csrf(csrf -> csrf.disable())
				.cors(cors -> cors.configurationSource(corsConfigurationSource()))

				.authorizeHttpRequests(authorize -> authorize
						.anyRequest().authenticated())

				.oauth2ResourceServer(oauth -> oauth
						.jwt(jwt -> jwt
								.jwtAuthenticationConverter(jwtAuthConverter)))
				.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.build();
	}

	//== CorsWebFilter ==//
	@Bean
	public CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration corsConfiguration = new CorsConfiguration();
		corsConfiguration.setAllowCredentials(true);
		corsConfiguration.setAllowedOrigins(Arrays.asList("http://localhost:8072"));
		corsConfiguration.setAllowedHeaders(Arrays.asList("*"));
		corsConfiguration.setAllowedMethods(Arrays.asList("GET","POST","PUT","DELETE", "OPTIONS"));

		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", corsConfiguration);
		return source;
	}

//	@Override
//	protected void configure(HttpSecurity http) throws Exception {
//		super.configure(http);
//		http.authorizeRequests()
//		.anyRequest().authenticated();
//		http.csrf().disable();
//	}

//	@Bean
//	@Override
//	protected SessionAuthenticationStrategy sessionAuthenticationStrategy() {
//		return new RegisterSessionAuthenticationStrategy(new SessionRegistryImpl());
//	}


//	@Autowired
//	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//		KeycloakAuthenticationProvider keycloakAuthenticationProvider = keycloakAuthenticationProvider();
//		keycloakAuthenticationProvider.setGrantedAuthoritiesMapper(new SimpleAuthorityMapper());
//		auth.authenticationProvider(keycloakAuthenticationProvider);
//	}
//
//
//	@Bean
//	public KeycloakConfigResolver KeycloakConfigResolver() {
//		return new KeycloakSpringBootConfigResolver();
//	}
//
//	@Bean
//	@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
//	public KeycloakRestTemplate keycloakRestTemplate() {
//		return new KeycloakRestTemplate(keycloakClientRequestFactory);
//	}
}

