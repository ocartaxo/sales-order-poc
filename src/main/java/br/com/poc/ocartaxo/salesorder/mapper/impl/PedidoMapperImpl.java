package br.com.poc.ocartaxo.salesorder.mapper.impl;

import br.com.poc.ocartaxo.salesorder.domain.pedido.PedidoResource;
import br.com.poc.ocartaxo.salesorder.dto.PedidoDetalhesResponse;
import br.com.poc.ocartaxo.salesorder.dto.PedidoResponse;
import br.com.poc.ocartaxo.salesorder.mapper.ClienteMapper;
import br.com.poc.ocartaxo.salesorder.mapper.PedidoMapper;
import br.com.poc.ocartaxo.salesorder.mapper.PedidoProdutoMapper;
import br.com.poc.ocartaxo.salesorder.model.Pedido;
import br.com.poc.ocartaxo.salesorder.service.EnderecoClienteService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;

@Slf4j
@Component
@RequiredArgsConstructor
public class PedidoMapperImpl implements PedidoMapper {

    private final EnderecoClienteService enderecoClienteService;
    private final ClienteMapper clienteMapper;
    private final PedidoProdutoMapper pedidoProdutoMapper;

    private static final DateTimeFormatter df = new DateTimeFormatterBuilder()
            .appendPattern("yyyy/MM/dd HH:mm:ss")
            .toFormatter();

    @Override
    public Pedido converteParaEntidade(PedidoResource pedidoResource) {
        log.info("Convertendo para entidade={}", pedidoResource);
        final var pedido = new Pedido();

        pedido.setCliente(pedidoResource.cliente());

        pedido.setProdutos(pedidoResource.produtosPedido());
        pedido.calculaValorTotal();


        final var enderecos = enderecoClienteService.getEnderecoEntregaCobranca(pedidoResource.cliente());
        pedido.setEnderecoCobranca(enderecos.EnderecoCobranca());
        pedido.setEnderecoEntrega(enderecos.enderecoEntrega());


        return pedido;
    }

    @Override
    public PedidoResponse converteParaDTO(Pedido pedido) {
        log.info("Convertendo para DTO resumida = {}", pedido);
        return new PedidoResponse(
                pedido.getId(),
                pedido.getValorTotal(),
                pedido.getData().format(df),
                pedido.getStatus()
        );
    }

    @Override
    public PedidoDetalhesResponse converteParaDetalhesDTO(Pedido pedido) {
        log.info("Converte para para DTO detalhada = {}", pedido);
        return new PedidoDetalhesResponse(
                pedido.getEnderecoEntrega(),
                pedido.getEnderecoCobranca(),
                pedido.getValorTotal(),
                pedido.getData().format(df),
                pedido.getStatus(),
                clienteMapper.converteParaDTO(pedido.getCliente()),
                pedido.getProdutos().stream().map(pedidoProdutoMapper::converteParaDto).toList()
        );
    }

}
