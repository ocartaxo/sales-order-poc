package br.com.poc.ocartaxo.salesorder.mapper.impl;

import br.com.poc.ocartaxo.salesorder.dto.PedidoDetalhesResponse;
import br.com.poc.ocartaxo.salesorder.dto.PedidoResponse;
import br.com.poc.ocartaxo.salesorder.mapper.ClienteMapper;
import br.com.poc.ocartaxo.salesorder.mapper.PedidoMapper;
import br.com.poc.ocartaxo.salesorder.mapper.ItemPedidoMapper;
import br.com.poc.ocartaxo.salesorder.model.Cliente;
import br.com.poc.ocartaxo.salesorder.model.Pedido;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;

@Slf4j
@Component
@RequiredArgsConstructor
public class PedidoMapperImpl implements PedidoMapper {

    private final EnderecoMapper enderecoMapper;
    private final ClienteMapper clienteMapper;
    private final ItemPedidoMapper itemPedidoMapper;

    private static final DateTimeFormatter df = new DateTimeFormatterBuilder()
            .appendPattern("yyyy/MM/dd HH:mm:ss")
            .toFormatter();

    @Override
    public Pedido converteParaEntidade(Cliente cliente) {

        final var pedido = new Pedido();

        pedido.setCliente(cliente);

        final var enderecos = enderecoMapper.converteEnderecosPedido(cliente.getEnderecos());
        pedido.setEnderecoCobranca(enderecos.enderecoCobranca());
        pedido.setEnderecoEntrega(enderecos.enderecoEntrega());

        return pedido;
    }

    @Override
    public PedidoResponse converteParaDTO(Pedido pedido) {
        log.info("Convertendo para DTO resumida, dto={}", pedido);
        return new PedidoResponse(
                pedido.getId(),
                pedido.getValorTotalPedido(),
                pedido.getData().format(df),
                pedido.getStatus()
        );
    }

    @Override
    public PedidoDetalhesResponse converteParaDetalhesDTO(Pedido pedido) {
        log.info("Converte para para DTO detalhada, dto={}", pedido);
        return new PedidoDetalhesResponse(
                pedido.getEnderecoEntrega(),
                pedido.getEnderecoCobranca(),
                pedido.getValorTotalPedido(),
                pedido.getData().format(df),
                pedido.getStatus(),
                clienteMapper.converteParaDTO(pedido.getCliente()),
                pedido.getProdutos().stream().map(itemPedidoMapper::converteParaDto).toList()
        );
    }

}
