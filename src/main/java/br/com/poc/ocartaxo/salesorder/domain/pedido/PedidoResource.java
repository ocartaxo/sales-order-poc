package br.com.poc.ocartaxo.salesorder.domain.pedido;

import br.com.poc.ocartaxo.salesorder.model.Cliente;
import br.com.poc.ocartaxo.salesorder.model.PedidoProduto;

import java.util.List;

public record PedidoResource(
        Cliente cliente,
        List<PedidoProduto> produtosPedido
) {
}
