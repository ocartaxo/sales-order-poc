package br.com.poc.ocartaxo.salesorder.infra.api;

import br.com.poc.ocartaxo.salesorder.infra.exception.CadastroClienteException;
import br.com.poc.ocartaxo.salesorder.infra.exception.CadastroProdutoException;
import br.com.poc.ocartaxo.salesorder.infra.exception.ClienteNaoEncontradoException;
import br.com.poc.ocartaxo.salesorder.infra.exception.ProdutoNaoEncontradoException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;

@RestControllerAdvice
public class ErrorHandler {

    private static final DateTimeFormatter df = new DateTimeFormatterBuilder()
            .appendPattern("yyyy/MM/dd HH:mm:ss")
            .toFormatter();

    @ExceptionHandler(CadastroClienteException.class)
    public ResponseEntity<ApiErrorResponse> gerenciaCadastroClienteError(
            CadastroClienteException ex,
            HttpServletRequest request
    ) {
        return ResponseEntity.badRequest().body(new ApiErrorResponse(
                LocalDateTime.now().format(df),
                HttpStatus.BAD_REQUEST.value(),
                request.getRequestURI(),
                "Ocorreu um erro ao tentar cadastrar o cliente.",
                ex.getMessage()

        ));
    }

    @ExceptionHandler(ClienteNaoEncontradoException.class)
    public ResponseEntity<ApiErrorResponse> gerenciaClienteNaoEncontrado(
            ClienteNaoEncontradoException ex,
            HttpServletRequest request
    ) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiErrorResponse(
                LocalDateTime.now().format(df),
                HttpStatus.NOT_FOUND.value(),
                request.getRequestURI(),
                "Ocorreu um erro ao buscar pelo cliente.",
                ex.getMessage()
        ));
    }

    @ExceptionHandler(CadastroProdutoException.class)
    public ResponseEntity<ApiErrorResponse> gerenciaErroCadastroProdutoError(
            CadastroProdutoException ex,
            HttpServletRequest request
    ){
        return ResponseEntity.badRequest().body(new ApiErrorResponse(
                LocalDateTime.now().format(df),
                HttpStatus.BAD_REQUEST.value(),
                request.getRequestURI(),
                "Ocorreu um erro ao cadastrar um novo produto",
                ex.getMessage()
        ));
    }

    @ExceptionHandler(ProdutoNaoEncontradoException.class)
    public ResponseEntity<ApiErrorResponse> gerenciaProdutoNaoEncontradoError(
            ProdutoNaoEncontradoException ex,
            HttpServletRequest request
    ) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiErrorResponse(
                LocalDateTime.now().format(df),
                HttpStatus.NOT_FOUND.value(),
                request.getRequestURI(),
                "Ocorreu um erro ao buscar pelo produto.",
                ex.getMessage()
        ));
    }
}
