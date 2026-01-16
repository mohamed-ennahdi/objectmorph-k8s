package com.github.mohamedennahdi.objectmorph.auth.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.github.mohamedennahdi.objectmorph.auth.client.ObjectmorphAuthClient;
import com.github.mohamedennahdi.objectmorph.auth.dto.SourceCodeDto;

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
