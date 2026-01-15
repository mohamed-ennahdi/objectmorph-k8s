package com.github.mohamedennahdi.objectmorph.auth.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ObjectmorphAuthExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(OAuth2AuthenticationException.class)
    public final ResponseEntity<ObjectmorphAuthErrorResponse> handleOAuth2AuthenticationException(final Exception ex, final WebRequest request) {
        final ObjectmorphAuthErrorResponse error = new ObjectmorphAuthErrorResponse("OAuth2 Error", ex.getLocalizedMessage());
        return new ResponseEntity<ObjectmorphAuthErrorResponse>(error, HttpStatus.UNAUTHORIZED);
    }
}
