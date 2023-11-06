package br.com.poc.ocartaxo.salesorder.infra.exception;

public class EmailInvalidoException extends CadastroClienteException {
    public EmailInvalidoException(String message) {
        super(message);
    }
}
