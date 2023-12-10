package br.com.poc.ocartaxo.salesorder.service.impl;

import br.com.poc.ocartaxo.salesorder.domain.pedido.PedidoResource;
import br.com.poc.ocartaxo.salesorder.dto.*;
import br.com.poc.ocartaxo.salesorder.infra.exception.PedidoNaoEncontradoException;
import br.com.poc.ocartaxo.salesorder.infra.exception.QuantidadeProdutoInsuficienteException;
import br.com.poc.ocartaxo.salesorder.mapper.PedidoMapper;
import br.com.poc.ocartaxo.salesorder.model.PedidoProduto;
import br.com.poc.ocartaxo.salesorder.model.Produto;
import br.com.poc.ocartaxo.salesorder.repository.ClientesRepository;
import br.com.poc.ocartaxo.salesorder.repository.PedidosRepository;
import br.com.poc.ocartaxo.salesorder.repository.ProdutosRepository;
import br.com.poc.ocartaxo.salesorder.service.PedidosService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class PedidosServiceImpl implements PedidosService {

    private final PedidoMapper mapper;

    private final PedidosRepository pedidosRepository;

    private final ClientesRepository clientesRepository;

    private final ProdutosRepository produtosRepository;


    @Override
    @Transactional
    public PedidoResponse cadastraNovoPedido(PedidoCadastroRequest body) {


        final var cliente = clientesRepository.findById(body.clienteId()).orElseThrow();
        final var produtos = buscaProdutos(body.produtos());

        final var pedido = mapper.converteParaEntidade(new PedidoResource(cliente, produtos));

        log.info("Cadastrando o pedido={}", pedido);
        pedidosRepository.save(pedido);

        return mapper.converteParaDTO(pedido);
    }

    @Override
    public PedidoDetalhesResponse buscarPedidoPorId(Long id) {

        final var pedido = pedidosRepository.findById(id).orElseThrow(() -> new PedidoNaoEncontradoException(id));

        return mapper.converteParaDetalhesDTO(pedido);
    }

    @Override
    public Page<PedidoResponse> listarTodosPedidos(Pageable pageable) {
        return pedidosRepository.findAll(pageable).map(mapper::converteParaDTO);
    }

    @Override
    @Transactional
    public PedidoDetalhesResponse atualizarPedido(Long id, PedidoAtualizacaoRequest body) {
        final var pedido = pedidosRepository.findById(id).orElseThrow(() -> new PedidoNaoEncontradoException(id));

        pedido.setStatus(body.status());

        return mapper.converteParaDetalhesDTO(pedido);
    }

    @Override
    public void deletarPorId(Long id) {
        pedidosRepository.deleteById(id);
    }

    private List<PedidoProduto> buscaProdutos(List<PedidoProdutoRequest> produtosPedido) {
        final var produtoIds = produtosPedido.stream().map(PedidoProdutoRequest::produtoId).toList();
        final var produtos = produtosRepository.findAllById(produtoIds);

        final var produtosHashTable = new HashMap<Long, Produto>();
        produtos.forEach(produto -> produtosHashTable.put(produto.getId(), produto));

        return produtosPedido.stream().map(pedidoProdutoRequest -> {
            final var produto = produtosHashTable.get(pedidoProdutoRequest.produtoId());
            final var quantidadeProdutoPedido = pedidoProdutoRequest.quantidade();

            if (quantidadeProdutoPedido > produto.getQuantidadeEstoque()) {
                log.error("Quantidade de produto {} insuficiente em estoque: Disponível={}, Desejada={}",
                        produto.getDescricao(), produto.getQuantidadeEstoque(), quantidadeProdutoPedido);

                throw new QuantidadeProdutoInsuficienteException(produto);
            }

            return new PedidoProduto(null, produto, quantidadeProdutoPedido);

        }).toList();
    }
}
