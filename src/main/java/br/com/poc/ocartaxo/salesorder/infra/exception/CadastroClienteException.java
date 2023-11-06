package br.com.poc.ocartaxo.salesorder.infra.exception;

public class CadastroClienteException extends RuntimeException {
    public CadastroClienteException(String message) {
        super(message);
    }
}
