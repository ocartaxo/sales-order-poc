package br.com.poc.ocartaxo.salesorder.mapper;

import br.com.poc.ocartaxo.salesorder.domain.pedido.EnderecosPedido;
import br.com.poc.ocartaxo.salesorder.dto.PedidoDetalhesResponse;
import br.com.poc.ocartaxo.salesorder.dto.PedidoResponse;
import br.com.poc.ocartaxo.salesorder.model.Cliente;
import br.com.poc.ocartaxo.salesorder.model.Endereco;
import br.com.poc.ocartaxo.salesorder.model.Pedido;

import java.util.List;

public interface PedidoMapper {
    Pedido converteParaEntidade(Cliente cliente);

    PedidoResponse converteParaDTO(Pedido pedido);

    PedidoDetalhesResponse converteParaDetalhesDTO(Pedido pedido);

    EnderecosPedido converteParaEnderecosPedido(List<Endereco> enderecos);
}
