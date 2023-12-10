package br.com.poc.ocartaxo.salesorder.dto;

public record PedidoProdutoRequest(
        int quantidade,
        Long produtoId
) {
}
