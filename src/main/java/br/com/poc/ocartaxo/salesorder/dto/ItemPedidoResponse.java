package br.com.poc.ocartaxo.salesorder.dto;

import java.math.BigDecimal;

public record ItemPedidoResponse(
        int quantidade,
        BigDecimal valorUnitario
) {
}
