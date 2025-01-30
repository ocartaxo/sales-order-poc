package br.com.poc.ocartaxo.salesorder.domain.pedido;

public record EnderecosPedido(
        String enderecoEntrega,
        String enderecoCobranca
) {
}
