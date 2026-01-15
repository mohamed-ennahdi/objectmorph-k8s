package com.github.mohamedennahdi.objectmorph.auth.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.github.mohamedennahdi.objectmorph.app.dto.SourceCodeDto;
import com.github.mohamedennahdi.objectmorph.auth.client.ObjectmorphAuthClient;

@Service
public class ObjectmorphAuthService {

	private final ObjectmorphAuthClient objectmorphAuthClient;

	public ObjectmorphAuthService(final ObjectmorphAuthClient objectmorphAuthClient) {
		this.objectmorphAuthClient = objectmorphAuthClient;
	}

	public ResponseEntity<String> html(final SourceCodeDto[] sourceCode) {
		return this.objectmorphAuthClient.html(sourceCode);
	}

}
