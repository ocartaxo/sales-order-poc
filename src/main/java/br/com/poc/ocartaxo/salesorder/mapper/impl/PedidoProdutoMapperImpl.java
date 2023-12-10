package br.com.poc.ocartaxo.salesorder.mapper.impl;

import br.com.poc.ocartaxo.salesorder.dto.PedidoProdutoResponse;
import br.com.poc.ocartaxo.salesorder.mapper.PedidoProdutoMapper;
import br.com.poc.ocartaxo.salesorder.model.PedidoProduto;
import org.springframework.stereotype.Component;

@Component
public class PedidoProdutoMapperImpl implements PedidoProdutoMapper {
    @Override
    public PedidoProdutoResponse converteParaDto(PedidoProduto produtoPedido) {
        return new PedidoProdutoResponse(produtoPedido.getId(), produtoPedido.getQuantidade(), produtoPedido.getProduto().getValor());
    }
}
