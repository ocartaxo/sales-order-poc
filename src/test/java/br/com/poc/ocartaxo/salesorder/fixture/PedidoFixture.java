package br.com.poc.ocartaxo.salesorder.fixture;

import br.com.poc.ocartaxo.salesorder.dto.PedidoCadastroRequest;
import br.com.poc.ocartaxo.salesorder.dto.PedidoProdutoRequest;
import br.com.poc.ocartaxo.salesorder.mapper.PedidoMapper;
import br.com.poc.ocartaxo.salesorder.mapper.impl.ItemPedidoMapperImpl;
import br.com.poc.ocartaxo.salesorder.mapper.impl.PedidoMapperImpl;
import br.com.poc.ocartaxo.salesorder.model.ItemPedido;
import br.com.poc.ocartaxo.salesorder.model.ItemPedidoPk;
import br.com.poc.ocartaxo.salesorder.model.Pedido;

import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.ArrayList;
import java.util.List;

public class PedidoFixture {

    public static Pedido pedido() {
        final var pedido = new Pedido();
        final var cliente = ClienteFixture.cliente();
        pedido.setId(0L);
        pedido.setCliente(cliente);
        pedido.setProdutos(itemPedidoList(pedido));
        pedido.setEnderecoCobranca(cliente.getEnderecos().get(0).getLogradouro());
        pedido.setProdutos(itemPedidoList(pedido));

        return pedido;
    }

    public static List<ItemPedido> itemPedidoList(Pedido pedido) {
        var itemPedidos = new ArrayList<ItemPedido>();
        for (var produto : ProdutoFixture.listaProdutos()){
            itemPedidos.add(new ItemPedido(
                    new ItemPedidoPk(pedido, produto), 1
            ));
        }

        return itemPedidos;
    }

    public static PedidoCadastroRequest pedidoCadastroRequest() {
        return new PedidoCadastroRequest(
                ClienteFixture.cliente().getId(),
                pedidoProdutoRequestList()
        );
    }

    public static List<PedidoProdutoRequest> pedidoProdutoRequestList() {
        var pedidoProdutoRequests = new ArrayList<PedidoProdutoRequest>();
        for (var produto : ProdutoFixture.listaProdutos()){
            pedidoProdutoRequests.add(
                    new PedidoProdutoRequest(1, produto.getId())
            );
        }
        return pedidoProdutoRequests;
    }

    public static PedidoMapper pedidoMapper() {
        return new PedidoMapperImpl(
                ClienteFixture.mapper(),
                new ItemPedidoMapperImpl()
        );
    }

    public static DateTimeFormatter formatter() {
        return new DateTimeFormatterBuilder()
                .appendPattern("yyyy/MM/dd HH:mm:ss")
                .toFormatter();
    }

}
