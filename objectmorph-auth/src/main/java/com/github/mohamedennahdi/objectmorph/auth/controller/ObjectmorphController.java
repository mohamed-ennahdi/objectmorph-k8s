package com.github.mohamedennahdi.objectmorph.auth.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.github.mohamedennahdi.objectmorph.app.dto.SourceCodeDto;
import com.github.mohamedennahdi.objectmorph.auth.service.ObjectmorphAuthService;

import io.swagger.v3.oas.annotations.Parameter;

@RestController
public class ObjectmorphController {

	private final ObjectmorphAuthService objectmorphAuthService;

	public ObjectmorphController(final ObjectmorphAuthService objectmorphAuthService) {
		this.objectmorphAuthService = objectmorphAuthService;
	}

	@PostMapping( value = "/api/v1/html" )
	public ResponseEntity<String> html(@Parameter(name = "sourceCode", description = "Source Code", example = "class SourceCode { int attribute1; int attribute2; SourceCode(){} public int getAttribute1() { return attribute1;}}")
	@RequestBody final	SourceCodeDto[] sourceCode) {
		return objectmorphAuthService.html(sourceCode);
	}
}
