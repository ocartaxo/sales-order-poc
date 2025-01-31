package br.com.poc.ocartaxo.salesorder.service;

import br.com.poc.ocartaxo.salesorder.dto.PedidoProdutoRequest;
import br.com.poc.ocartaxo.salesorder.dto.PedidoResponse;
import br.com.poc.ocartaxo.salesorder.enums.StatusPedido;
import br.com.poc.ocartaxo.salesorder.fixture.ClienteFixture;
import br.com.poc.ocartaxo.salesorder.fixture.PedidoFixture;
import br.com.poc.ocartaxo.salesorder.fixture.ProdutoFixture;
import br.com.poc.ocartaxo.salesorder.model.Pedido;
import br.com.poc.ocartaxo.salesorder.repository.ClientesRepository;
import br.com.poc.ocartaxo.salesorder.repository.PedidosRepository;
import br.com.poc.ocartaxo.salesorder.repository.ProdutosRepository;
import br.com.poc.ocartaxo.salesorder.service.impl.PedidosServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PedidoServiceTest {


    private final PedidosRepository pedidosRepository = mock(PedidosRepository.class);


    private final ClientesRepository clientesRepository = mock(ClientesRepository.class);


    private final ProdutosRepository produtosRepository = mock(ProdutosRepository.class);

    private PedidosService pedidosService;

    @BeforeEach
    void setup() {
        pedidosService = new PedidosServiceImpl(PedidoFixture.pedidoMapper(),
                pedidosRepository,
                clientesRepository,
                produtosRepository
        );
    }

    @Test
    @DisplayName("Deve criar pedido com sucesso quando os dados de cadastro forem válidos")
    public void testaCriacaoPedidoSemErro() {
        var cadastroPedido = PedidoFixture.pedidoCadastroRequest();

        when(clientesRepository.findById(cadastroPedido.clienteId())).thenReturn(Optional.of(ClienteFixture.cliente()));
        when(produtosRepository.findAllById(
                        cadastroPedido.produtos().stream().map(PedidoProdutoRequest::produtoId).toList())
        ).thenReturn(ProdutoFixture.listaProdutos());

        when(pedidosRepository.save(any(Pedido.class))).thenReturn(PedidoFixture.pedido());

        assertDoesNotThrow(() -> pedidosService.cadastraNovoPedido(cadastroPedido));
    }

    @Test
    @DisplayName("Quando pedido for criado com sucesso os campos devem ser válidos")
    void testeCamposPedidoCriado() {
        var cadastroPedido = PedidoFixture.pedidoCadastroRequest();

        when(clientesRepository.findById(cadastroPedido.clienteId())).thenReturn(Optional.of(ClienteFixture.cliente()));
        when(produtosRepository.findAllById(
                cadastroPedido.produtos().stream().map(PedidoProdutoRequest::produtoId).toList())
        ).thenReturn(ProdutoFixture.listaProdutos());

        when(pedidosRepository.save(any(Pedido.class))).thenReturn(PedidoFixture.pedido());

        final var response = pedidosService.cadastraNovoPedido(cadastroPedido);
        assertAll(
                () -> assertNotNull(response.id()),
                () -> assertInstanceOf(PedidoResponse.class, response),
                () -> assertEquals(StatusPedido.CONFIRMADO, response.status()),
                () -> {
                    final var now = LocalDateTime.now().format(PedidoFixture.formatter());
                    assertEquals(now, response.data());
                },
                () -> assertTrue(response.valorTotal().compareTo(BigDecimal.ONE) > 0)
        );
    }
}
