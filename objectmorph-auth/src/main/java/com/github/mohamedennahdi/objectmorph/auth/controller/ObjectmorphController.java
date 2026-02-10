package com.github.mohamedennahdi.objectmorph.auth.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.github.mohamedennahdi.objectmorph.auth.dto.SourceCodeDto;
import com.github.mohamedennahdi.objectmorph.auth.service.ObjectmorphAuthService;

@RestController
public class ObjectmorphController {

	private final ObjectmorphAuthService objectmorphAuthService;

	public ObjectmorphController(final ObjectmorphAuthService objectmorphAuthService) {
		this.objectmorphAuthService = objectmorphAuthService;
	}

	@PostMapping( value = "/api/v1/html" )
	public ResponseEntity<String> html(@RequestBody final SourceCodeDto[] sourceCode) {
		return objectmorphAuthService.html(sourceCode);
	}
}
