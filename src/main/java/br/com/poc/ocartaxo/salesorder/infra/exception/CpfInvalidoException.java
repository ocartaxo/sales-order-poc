package br.com.poc.ocartaxo.salesorder.infra.exception;

public class CpfInvalidoException extends IllegalArgumentException {
    public CpfInvalidoException(String message) {
        super(message);
    }
}
