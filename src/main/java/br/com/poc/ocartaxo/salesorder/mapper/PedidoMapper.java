package br.com.poc.ocartaxo.salesorder.mapper;

import br.com.poc.ocartaxo.salesorder.dto.PedidoCadastroRequest;
import br.com.poc.ocartaxo.salesorder.dto.PedidoDetalhesResponse;
import br.com.poc.ocartaxo.salesorder.dto.PedidoResponse;
import br.com.poc.ocartaxo.salesorder.model.Pedido;

public interface PedidoMapper {
    Pedido converteParaEntidade(PedidoCadastroRequest body);

    PedidoResponse converteParaDTO(Pedido pedido);

    PedidoDetalhesResponse converteParaDetalhesDTO(Pedido pedido);
}
