package br.com.poc.ocartaxo.salesorder.infra.exception;

public class PedidoInvalidoException extends IllegalArgumentException {
    public PedidoInvalidoException(String message) {
        super(message);
    }
}
