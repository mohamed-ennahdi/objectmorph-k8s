package com.github.mohamedennahdi.objectmorph.auth.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.github.mohamedennahdi.objectmorph.auth.dto.ObjectmorphAuthErrorResponseDto;

import feign.FeignException;
import feign.RetryableException;
import feign.codec.EncodeException;

@ControllerAdvice
public class ObjectmorphAuthExceptionHandler extends ResponseEntityExceptionHandler {
	@ExceptionHandler(OAuth2AuthenticationException.class)
	public final ResponseEntity<ObjectmorphAuthErrorResponseDto> handleOAuth2AuthenticationException(final Exception ex, final WebRequest request) {
		final ObjectmorphAuthErrorResponseDto error = new ObjectmorphAuthErrorResponseDto("OAuth2 Error", ex.getLocalizedMessage());
		return new ResponseEntity<ObjectmorphAuthErrorResponseDto>(error, HttpStatus.UNAUTHORIZED);
	}

	@ExceptionHandler({HttpClientErrorException.class, EncodeException.class, FeignException.class})
	public final ResponseEntity<ObjectmorphAuthErrorResponseDto> handleHttpClientErrorException(final Exception ex, final WebRequest request) {
		final ObjectmorphAuthErrorResponseDto error = new ObjectmorphAuthErrorResponseDto(ex.getClass().getName(), ex.getLocalizedMessage());
		return new ResponseEntity<ObjectmorphAuthErrorResponseDto>(error, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(RetryableException.class)
	public final ResponseEntity<ObjectmorphAuthErrorResponseDto> handleRetryableException(final Exception ex, final WebRequest request) {
		final ObjectmorphAuthErrorResponseDto error = new ObjectmorphAuthErrorResponseDto("Retryable Error", ex.getLocalizedMessage());
		return new ResponseEntity<ObjectmorphAuthErrorResponseDto>(error, HttpStatus.SERVICE_UNAVAILABLE);
	}

}
