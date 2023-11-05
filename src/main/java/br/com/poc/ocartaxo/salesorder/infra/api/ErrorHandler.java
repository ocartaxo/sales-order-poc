package br.com.poc.ocartaxo.salesorder.infra.api;

import br.com.poc.ocartaxo.salesorder.infra.exception.CadastroClienteException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class ErrorHandler {

    @ExceptionHandler(CadastroClienteException.class)
    public ResponseEntity<?> gerenciaCadastroClienteError(
            CadastroClienteException ex,
            HttpServletRequest request
    ) {
        return ResponseEntity.badRequest().body(new ApiErrorResponse(
                LocalDateTime.now(),
                HttpStatus.BAD_REQUEST.value(),
                request.getRequestURI(),
                ex.getMessage(),
                ex.getLocalizedMessage()
        ));
    }
}
