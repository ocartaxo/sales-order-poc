package br.com.poc.ocartaxo.salesorder.infra.exception;

public class CadastroClienteException extends IllegalArgumentException {
    public CadastroClienteException(String message) {
        super(message);
    }
}
