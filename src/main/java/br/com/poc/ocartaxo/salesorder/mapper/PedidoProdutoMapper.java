package br.com.poc.ocartaxo.salesorder.mapper;

import br.com.poc.ocartaxo.salesorder.dto.PedidoProdutoResponse;
import br.com.poc.ocartaxo.salesorder.model.PedidoProduto;

public interface PedidoProdutoMapper {

    PedidoProdutoResponse converteParaDto(PedidoProduto produtoPedido);



}
