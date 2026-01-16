package com.github.mohamedennahdi.objectmorph.auth.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;

import com.github.mohamedennahdi.objectmorph.auth.dto.SourceCodeDto;


@FeignClient(name = "objectmorph-app-client")
public interface ObjectmorphAuthClient {
	@PostMapping( value = "/html" )
	public ResponseEntity<String> html(SourceCodeDto[] sourceCode);
}
