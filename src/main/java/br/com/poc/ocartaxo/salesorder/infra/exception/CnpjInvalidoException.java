package br.com.poc.ocartaxo.salesorder.infra.exception;

public class CnpjInvalidoException extends IllegalArgumentException {
    public CnpjInvalidoException(String message) {
        super(message);
    }
}
