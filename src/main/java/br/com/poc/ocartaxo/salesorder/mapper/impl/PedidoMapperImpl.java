package br.com.poc.ocartaxo.salesorder.mapper.impl;

import br.com.poc.ocartaxo.salesorder.dto.*;
import br.com.poc.ocartaxo.salesorder.enums.TipoEndereco;
import br.com.poc.ocartaxo.salesorder.mapper.ClienteMapper;
import br.com.poc.ocartaxo.salesorder.mapper.PedidoMapper;
import br.com.poc.ocartaxo.salesorder.mapper.ProdutoMapper;
import br.com.poc.ocartaxo.salesorder.model.Pedido;
import br.com.poc.ocartaxo.salesorder.model.Produto;
import br.com.poc.ocartaxo.salesorder.service.PedidoClienteService;
import br.com.poc.ocartaxo.salesorder.service.PedidoProdutoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class PedidoMapperImpl implements PedidoMapper {

    private final PedidoProdutoService pedidoProdutoService;
    private final PedidoClienteService pedidoClienteService;
    private final ClienteMapper clienteMapper;
    private final ProdutoMapper produtoMapper;

    private static final DateTimeFormatter df = new DateTimeFormatterBuilder()
            .appendPattern("yyyy/MM/dd HH:mm:ss")
            .toFormatter();

    @Override
    public Pedido converteParaEntidade(PedidoCadastroRequest body) {
        final var pedido = new Pedido();

        final var cliente = pedidoClienteService.buscaClientePorId(body.clienteId());
        pedido.setCliente(cliente);

        final var produtos = pedidoProdutoService.buscaProdutosPorId(body.produtos().stream().map(ItensPedido::produtoId).toList());
        pedido.setItensPedido(produtos);

        final var pedidosResource = body.produtos().stream().map(pp -> new ItensPedidoResource(
                pp.quantidade(),
                produtos.stream().filter(p -> Objects.equals(pp.produtoId(), p.getId())).findFirst().orElseThrow().getValor()
        )).toList();

        pedido.setValorTotal(valorTotal(pedidosResource));

        final var enderecos = pedidoClienteService.getEnderecoEntregaCobranca(cliente);
        pedido.setEnderecoCobranca(enderecos.EnderecoCobranca());
        pedido.setEnderecoEntrega(enderecos.enderecoEntrega());

        pedido.setDataCriacao(LocalDateTime.now());

        return pedido;
    }

    @Override
    public PedidoResponse converteParaDTO(Pedido pedido) {
        return new PedidoResponse(pedido.getId(), pedido.getValorTotal(), pedido.getDataCriacao().format(df), pedido.getStatus());
    }

    @Override
    public PedidoDetalhesResponse converteParaDetalhesDTO(Pedido pedido) {
        return new PedidoDetalhesResponse(
                pedido.getEnderecoEntrega(),
                pedido.getEnderecoCobranca(),
                pedido.getValorTotal(),
                pedido.getDataCriacao().format(df),
                pedido.getStatus(),
                clienteMapper.converteParaDTO(pedido.getCliente()),
                pedido.getItensPedido().stream().map(produtoMapper::converteParaDto).sorted(Comparator.comparingLong(ProdutoResponse::id)).toList()
        );
    }

    private BigDecimal valorTotal(List<ItensPedidoResource> itensPedidoResources) {

//        itensPedidoResources.stream().reduce(BigDecimal.ZERO,);
        var acc = BigDecimal.ZERO;
        for (final var r : itensPedidoResources){
            acc = acc.add(r.valor().multiply(BigDecimal.valueOf(r.quantidade())));
        }

        return acc;
    }
}
