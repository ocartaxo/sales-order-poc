package br.com.poc.ocartaxo.salesorder.mapper;

import br.com.poc.ocartaxo.salesorder.dto.PedidoDetalhesResponse;
import br.com.poc.ocartaxo.salesorder.dto.PedidoResponse;
import br.com.poc.ocartaxo.salesorder.model.Cliente;
import br.com.poc.ocartaxo.salesorder.model.Pedido;

public interface PedidoMapper {
    Pedido converteParaEntidade(Cliente cliente);

    PedidoResponse converteParaDTO(Pedido pedido);

    PedidoDetalhesResponse converteParaDetalhesDTO(Pedido pedido);
}
