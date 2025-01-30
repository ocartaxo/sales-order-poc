package br.com.poc.ocartaxo.salesorder.fixture;

import br.com.poc.ocartaxo.salesorder.mapper.PedidoMapper;
import br.com.poc.ocartaxo.salesorder.mapper.impl.ItemPedidoMapperImpl;
import br.com.poc.ocartaxo.salesorder.mapper.impl.PedidoMapperImpl;
import br.com.poc.ocartaxo.salesorder.model.ItemPedido;
import br.com.poc.ocartaxo.salesorder.model.ItemPedidoPk;
import br.com.poc.ocartaxo.salesorder.model.Pedido;

import java.util.ArrayList;
import java.util.List;

public class PedidoFixture {

    public static Pedido pedido() {
        final var pedido = new Pedido();
        final var cliente = ClienteFixture.cliente();
        pedido.setId(0L);
        pedido.setCliente(cliente);
        pedido.setProdutos(itemPedidoList(pedido));
        pedido.setEnderecoCobranca(cliente.getEnderecos().get(0).toString());
        pedido.setProdutos(itemPedidoList(pedido));

        return pedido;
    }

    public static List<ItemPedido> itemPedidoList(Pedido pedido) {
        var itemPedidos = new ArrayList<ItemPedido>();
        for (int idx : List.of(1, 2 ,3, 4, 5)){
            var produto = ProdutoFixture.produto();
            produto.setDescricao("Celular %d".formatted(idx));
            itemPedidos.add(new ItemPedido(
                    new ItemPedidoPk(pedido, produto), 3)
            );
        }

        return itemPedidos;
    }

    public static PedidoMapper pedidoMapper() {
        return new PedidoMapperImpl(
                ClienteFixture.mapper(),
                new ItemPedidoMapperImpl()
        );
    }

}
