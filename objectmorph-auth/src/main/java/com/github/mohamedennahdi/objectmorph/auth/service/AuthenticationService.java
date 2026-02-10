package com.github.mohamedennahdi.objectmorph.auth.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClient;

import com.github.mohamedennahdi.objectmorph.auth.dto.AuthenticationDto;
import com.github.mohamedennahdi.objectmorph.auth.dto.AuthenticationResponseDto;

@Service
public class AuthenticationService {
	private final RestClient restClient;
	private final String uri;
	private final String clientId;
	private final String clientSecret;

	public AuthenticationService(final RestClient.Builder restClientBuilder
			, @Value("${spring.security.oauth2.client.provider.keycloak.issuer-uri}") final String uri
			, @Value("${spring.security.oauth2.client.registration.keycloak.client-id}") final String clientId
			, @Value("${spring.security.oauth2.client.registration.keycloak.client-secret}") final String clientSecret
			) {
		this.clientId = clientId;
		this.clientSecret = clientSecret;
		this.uri = uri;
		this.restClient = restClientBuilder.baseUrl(this.uri).build();
	}

	public AuthenticationResponseDto authenticate(final AuthenticationDto dto) {
		final MultiValueMap<String, String> requestBody = new LinkedMultiValueMap<>();
		requestBody.add("grant_type", "password");
		requestBody.add("client_id", clientId);
		requestBody.add("client_secret", clientSecret);
		requestBody.add("username", dto.username());
		requestBody.add("password", dto.password());

		return restClient.post().uri("/protocol/openid-connect/token")
				.contentType(MediaType.APPLICATION_FORM_URLENCODED)
				.body(requestBody)
				.retrieve()
				.body(AuthenticationResponseDto.class);
	}
}
