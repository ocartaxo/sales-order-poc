package br.com.poc.ocartaxo.salesorder.mapper.impl;

import br.com.poc.ocartaxo.salesorder.domain.pedido.EnderecosPedido;
import br.com.poc.ocartaxo.salesorder.dto.PedidoDetalhesResponse;
import br.com.poc.ocartaxo.salesorder.dto.PedidoResponse;
import br.com.poc.ocartaxo.salesorder.enums.TipoEndereco;
import br.com.poc.ocartaxo.salesorder.infra.exception.CadastroPedidoException;
import br.com.poc.ocartaxo.salesorder.mapper.ClienteMapper;
import br.com.poc.ocartaxo.salesorder.mapper.PedidoMapper;
import br.com.poc.ocartaxo.salesorder.mapper.ItemPedidoMapper;
import br.com.poc.ocartaxo.salesorder.model.Cliente;
import br.com.poc.ocartaxo.salesorder.model.Endereco;
import br.com.poc.ocartaxo.salesorder.model.Pedido;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class PedidoMapperImpl implements PedidoMapper {

    private final ClienteMapper clienteMapper;
    private final ItemPedidoMapper itemPedidoMapper;

    private static final DateTimeFormatter df = new DateTimeFormatterBuilder()
            .appendPattern("yyyy/MM/dd HH:mm:ss")
            .toFormatter();

    @Override
    public Pedido converteParaEntidade(Cliente cliente) {

        final var pedido = new Pedido();

        pedido.setCliente(cliente);

        final var enderecos = this.converteParaEnderecosPedido(cliente.getEnderecos());
        pedido.setEnderecoCobranca(enderecos.enderecoCobranca());
        pedido.setEnderecoEntrega(enderecos.enderecoEntrega());

        return pedido;
    }

    @Override
    public PedidoResponse converteParaDTO(Pedido pedido) {
        log.info("Convertendo entidade para DTO resumida, dto");
        return new PedidoResponse(
                pedido.getId(),
                pedido.getValorTotalPedido(),
                pedido.getData().format(df),
                pedido.getStatus()
        );
    }

    @Override
    public PedidoDetalhesResponse converteParaDetalhesDTO(Pedido pedido) {
        log.info("Converte para para DTO detalhada");
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

    @Override
    public EnderecosPedido converteParaEnderecosPedido(List<Endereco> enderecos) {
        log.info("Enderecos={}", enderecos);

        final var enderecoAll = enderecos.stream().filter(e-> e.getTipoEndereco() == TipoEndereco.ALL).findFirst();
        if (enderecoAll.isPresent()){
            return new EnderecosPedido(enderecoAll.get().getLogradouro(), enderecoAll.get().getLogradouro());
        }

        final var enderecoCobranca = enderecos.stream()
                .filter( e -> TipoEndereco.COBRANCA.equals(e.getTipoEndereco()))
                .findFirst()
                .orElseThrow(() ->
                        new CadastroPedidoException("Endereço de cobrança não encontrado")
                );

        final var enderecoEntrega = enderecos.stream()
                .filter(e -> TipoEndereco.ENTREGA.equals(e.getTipoEndereco()))
                .findFirst()
                .orElseThrow(() ->
                        new CadastroPedidoException("Endereço de entrega não encontrado")
                );

        return new EnderecosPedido(enderecoEntrega.getLogradouro(), enderecoCobranca.getLogradouro());

    }
}
