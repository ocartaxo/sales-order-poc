package br.com.poc.ocartaxo.salesorder.mapper.impl;

import br.com.poc.ocartaxo.salesorder.dto.ItemPedidoResponse;
import br.com.poc.ocartaxo.salesorder.mapper.ItemPedidoMapper;
import br.com.poc.ocartaxo.salesorder.model.ItemPedido;
import org.springframework.stereotype.Component;

@Component
public class ItemPedidoMapperImpl implements ItemPedidoMapper {
    @Override
    public ItemPedidoResponse converteParaDto(ItemPedido produtoPedido) {
        return new ItemPedidoResponse(produtoPedido.getQuantidade(), produtoPedido.getProduto().getValor());
    }
}
