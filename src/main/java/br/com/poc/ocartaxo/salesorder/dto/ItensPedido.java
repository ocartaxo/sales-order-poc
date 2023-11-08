package br.com.poc.ocartaxo.salesorder.dto;

public record ItensPedido(
        int quantidade,
        Long produtoId
) {
}
