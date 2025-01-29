package br.com.poc.ocartaxo.salesorder.infra.exception;

public class DocumentoInvalidoException extends IllegalArgumentException {
    public DocumentoInvalidoException(String message) {
        super(message);
    }
}
