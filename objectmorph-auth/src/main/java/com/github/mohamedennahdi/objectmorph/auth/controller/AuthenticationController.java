package com.github.mohamedennahdi.objectmorph.auth.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;

import com.github.mohamedennahdi.objectmorph.auth.dto.AuthenticationDto;
import com.github.mohamedennahdi.objectmorph.auth.dto.AuthenticationResponseDto;
import com.github.mohamedennahdi.objectmorph.auth.service.AuthenticationService;

@RestController
public class AuthenticationController {

	private final AuthenticationService authenticationService;

	public AuthenticationController(final AuthenticationService authenticationService) {
		this.authenticationService = authenticationService;
	}

	@PostMapping( value = "/api/v1/authenticate" )
	public ResponseEntity<AuthenticationResponseDto> authenticate(@RequestBody final AuthenticationDto dto) {
		try {
			final AuthenticationResponseDto responseDto = authenticationService.authenticate(dto);
			return new ResponseEntity<>(responseDto, HttpStatus.OK);
		} catch (final HttpClientErrorException e) {
			throw e;
		}
	}
}
