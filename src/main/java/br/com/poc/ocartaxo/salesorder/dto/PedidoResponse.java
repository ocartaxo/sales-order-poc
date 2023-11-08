package br.com.poc.ocartaxo.salesorder.dto;

import br.com.poc.ocartaxo.salesorder.enums.StatusPedido;

import java.math.BigDecimal;

public record PedidoResponse(
        Long id,
        BigDecimal valorTotal,
        String data,
        StatusPedido status
) {
}
