package br.com.poc.ocartaxo.salesorder.dto;

import br.com.poc.ocartaxo.salesorder.enums.StatusPedido;

public record PedidoAtualizacaoRequest(
    StatusPedido status
) {
}
