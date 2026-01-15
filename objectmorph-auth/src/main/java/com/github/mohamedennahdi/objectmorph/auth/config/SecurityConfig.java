package com.github.mohamedennahdi.objectmorph.auth.config;

import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.client.oidc.web.logout.OidcClientInitiatedLogoutSuccessHandler;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtDecoders;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;

/**
 * SecurityConfig class configures security settings for the application,
 * enabling security filters and setting up OAuth2 login and logout behavior.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

	/**
	 * Configures the security filter chain for handling HTTP requests, OAuth2 login, and logout.
	 *
	 * @param http HttpSecurity object to define web-based security at the HTTP level
	 * @return SecurityFilterChain for filtering and securing HTTP requests
	 * @throws Exception in case of an error during configuration
	 */

	public SecurityConfig(final ClientRegistrationRepository clientRegistrationRepository) {
		this.clientRegistrationRepository = clientRegistrationRepository;
	}

	@Bean
	public SecurityFilterChain filterChain(final HttpSecurity http/*, final LogoutSuccessHandler logoutSuccessHandler*/) throws Exception {
		http
		// Configures authorization rules for different endpoints
		.authorizeHttpRequests(authorize -> authorize
				.requestMatchers("/").permitAll() // Allows public access to the root URL
				.requestMatchers("/swagger-ui/*").permitAll() // Allows public access to the root URL
				.requestMatchers("/v3/api-docs/*").permitAll() // Allows public access to the root URL
				.requestMatchers("/v3/api-docs*").permitAll() // Allows public access to the root URL
				.anyRequest().authenticated() // Requires authentication for any other request
				)
		.oauth2ResourceServer(oauth2 -> oauth2.jwt(Customizer.withDefaults())
				)

		// Configures logout settings
		.logout(logout -> logout
				.logoutSuccessUrl("/") // Redirects to the root URL on successful logout
				.invalidateHttpSession(true) // Invalidates session to clear session data
				.clearAuthentication(true) // Clears authentication details
				.deleteCookies("JSESSIONID") // Deletes the session cookie
				.logoutSuccessHandler(oidcLogoutSuccessHandler())
				);

		return http.build();
	}

	private final ClientRegistrationRepository clientRegistrationRepository;

	private LogoutSuccessHandler oidcLogoutSuccessHandler() {
		final OidcClientInitiatedLogoutSuccessHandler oidcLogoutSuccessHandler = new OidcClientInitiatedLogoutSuccessHandler(this.clientRegistrationRepository);

		oidcLogoutSuccessHandler.setPostLogoutRedirectUri("{baseUrl}?logoutsuccess=true");

		return oidcLogoutSuccessHandler;
	}

	@Bean
	public OpenAPI openAPI() {
		return new OpenAPI().addSecurityItem(new SecurityRequirement().
				addList("Bearer Authentication"))
				.components(new Components().addSecuritySchemes
						("Bearer Authentication", createAPIKeyScheme()))
				.info(new Info().title("ObjectMorph REST API")
						.description("Authorization module for ObjectMorph API.")
						.version("1.0").contact(new Contact().name("Mohamed ENNAHDI EL IDRISSI")
								.email( "www.github.com/mohamed-ennahdi").url("mohamed.ennahdi@gmail.com"))
						.license(new License().name("Apache-2.0 license")
								.url("https://github.com/mohamed-ennahdi/objectmorph-k8s?tab=Apache-2.0-1-ov-file#readme")));
	}

	private SecurityScheme createAPIKeyScheme() {
		return new SecurityScheme().type(SecurityScheme.Type.HTTP)
				.bearerFormat("JWT")
				.scheme("bearer");
	}


	@Bean
	public JwtDecoder jwtDecoder(final OAuth2ResourceServerProperties properties) {
		return JwtDecoders.fromIssuerLocation(properties.getJwt().getIssuerUri());
	}

}
