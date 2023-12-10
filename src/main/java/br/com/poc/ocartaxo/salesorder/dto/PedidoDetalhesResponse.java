package br.com.poc.ocartaxo.salesorder.dto;

import br.com.poc.ocartaxo.salesorder.enums.StatusPedido;

import java.math.BigDecimal;
import java.util.List;

public record PedidoDetalhesResponse(

        String enderecoEntrega,
        String enderecoCobranca,
        BigDecimal valorTotal,
        String data,
        StatusPedido statusPedido,
        ClienteResponse cliente,
        List<PedidoProdutoResponse> produtos

) {
}
