package br.com.poc.ocartaxo.salesorder.mapper;

import br.com.poc.ocartaxo.salesorder.dto.ItemPedidoResponse;
import br.com.poc.ocartaxo.salesorder.model.ItemPedido;

public interface ItemPedidoMapper {

    ItemPedidoResponse converteParaDto(ItemPedido produtoPedido);



}
