package br.com.poc.ocartaxo.salesorder.infra.exception;

public class InformacoesClienteInvalidaException extends RuntimeException {
    public InformacoesClienteInvalidaException(String message) {
        super(message);
    }
}
