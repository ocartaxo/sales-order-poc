package br.com.poc.ocartaxo.salesorder.fixture;

import br.com.poc.ocartaxo.salesorder.model.ItemPedido;
import br.com.poc.ocartaxo.salesorder.model.Pedido;

import java.util.List;

public class PedidoFixture {

    public static Pedido pedidoExistente() {
        final var pedido = new Pedido();
        final var cliente = ClienteFixture.cliente();
        pedido.setId(0L);
        pedido.setCliente(cliente);
        pedido.setProdutos(itemPedidoList());
        pedido.setEnderecoCobranca(cliente.getEnderecos().get(0).toString());


        return pedido;
    }

    public static List<ItemPedido> itemPedidoList() {
        return List.of(

        )
    }

}
