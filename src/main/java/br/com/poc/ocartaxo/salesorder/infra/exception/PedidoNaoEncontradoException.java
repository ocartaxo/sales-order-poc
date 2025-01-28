package br.com.poc.ocartaxo.salesorder.infra.exception;

public class PedidoNaoEncontradoException extends RuntimeException {
    public PedidoNaoEncontradoException(Long id) {
        super("O pedido de id `%d` não foi encontrado".formatted(id));
    }
}
