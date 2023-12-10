package br.com.poc.ocartaxo.salesorder.dto;

import java.math.BigDecimal;

public record PedidoProdutoResponse(
        Long produtoId,
        int quantidade,
        BigDecimal valorUnitario
) {
}
