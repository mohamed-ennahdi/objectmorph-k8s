package com.github.mohamedennahdi.objectmorph.auth.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;

import com.github.mohamedennahdi.objectmorph.app.dto.SourceCodeDto;

@FeignClient(name = "objectmorph-html", url = "http://localhost:8080/")
public interface ObjectmorphAuthClient {
	@PostMapping( value = "/html" )
	public ResponseEntity<String> html(SourceCodeDto[] sourceCode);
}
