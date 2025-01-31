package br.com.poc.ocartaxo.salesorder.service.impl;

import br.com.poc.ocartaxo.salesorder.dto.*;
import br.com.poc.ocartaxo.salesorder.infra.exception.PedidoInvalidoException;
import br.com.poc.ocartaxo.salesorder.infra.exception.PedidoNaoEncontradoException;
import br.com.poc.ocartaxo.salesorder.mapper.PedidoMapper;
import br.com.poc.ocartaxo.salesorder.model.ItemPedido;
import br.com.poc.ocartaxo.salesorder.model.ItemPedidoPk;
import br.com.poc.ocartaxo.salesorder.model.Pedido;
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
import java.util.Map.Entry;

@Slf4j
@Service
@RequiredArgsConstructor
public class PedidosServiceImpl implements PedidosService {

    private final PedidoMapper mapper;

    private final PedidosRepository pedidosRepository;

    private final ClientesRepository clientesRepository;

    private final ProdutosRepository produtosRepository;

    /**
     * Cria um novo pedido
     * @param body Informações necessárias para criar um pedido
     * @return Informações do pedido criado
     * @throws PedidoInvalidoException
     */
    @Override
    @Transactional
    public PedidoResponse cadastraNovoPedido(PedidoCadastroRequest body) {


        final var cliente = clientesRepository.findById(body.clienteId()).orElseThrow(() ->
                new PedidoInvalidoException("Não é possível criar o pedido para o cliente %d não cadastrado"
                        .formatted(body.clienteId())));

        var pedido = mapper.converteParaEntidade(cliente);
        pedido.setProdutos(verificaDisponibilidadeProdutos(body.produtos(), pedido));

        pedido = pedidosRepository.save(pedido);
        log.info("Pedido cadastrado com sucesso! Pedido: {}", pedido);

        return mapper.converteParaDTO(pedido);
    }

    /**
     * Busca as informações de um pedido que corresnponda ao id informado
     * @param id Id do produto na base
     * @return Detalhes do pedido
     */
    @Override
    public PedidoDetalhesResponse buscarPedidoPorId(Long id) {

        final var pedido = pedidosRepository.findById(id).orElseThrow(() -> new PedidoNaoEncontradoException(id));

        return mapper.converteParaDetalhesDTO(pedido);
    }

    /**
     * Busca uma lista de pedidos que corresponda aos filtros
     * @param pageable Filtros aplicados na busca
     * @return Uma página com informações do pedido
     */
    @Override
    public Page<PedidoResponse> listarTodosPedidos(Pageable pageable) {
        return pedidosRepository.findAll(pageable).map(mapper::converteParaDTO);
    }


    /**
     * Atualiza as informações de um pedido que corresponda ao id informado
     * @param id Id do pedido que deseja atualizar
     * @param body Informações que serão atualizadas no pedido
     * @return Informações atualizadas do pedido
     */
    @Override
    @Transactional
    public PedidoDetalhesResponse atualizarPedido(Long id, PedidoAtualizacaoRequest body) {
        final var pedido = pedidosRepository.findById(id).orElseThrow(() -> new PedidoNaoEncontradoException(id));

        pedido.setStatus(body.status());

        return mapper.converteParaDetalhesDTO(pedido);
    }

    /**
     * Remove o pedido da base que corresponda ao id informado
     * @param id Id do produto que será removido
     */

    @Override
    public void deletarPorId(Long id) {
        pedidosRepository.deleteById(id);
    }

    private List<ItemPedido> verificaDisponibilidadeProdutos(
            List<PedidoProdutoRequest> produtosPedido,
            Pedido pedido
    ) {

        final var quantidadeProdutoEstoque = getQuantidadeProdutoEstoque(produtosPedido);

        return quantidadeProdutoEstoque.entrySet().stream().map(entry -> validaProdutoEstoque(pedido, entry)).toList();

    }

    private ItemPedido validaProdutoEstoque(Pedido pedido, Entry<Produto, Integer> produtoQuantidade) {

        final var produto = produtoQuantidade.getKey();
        final var quantidadePedido = produtoQuantidade.getValue();

        if (quantidadePedido > produto.getQuantidadeEstoque()) {
            log.error("Quantidade de produto {} insuficiente em estoque: Disponível={}, Desejada={}",
                    produto.getDescricao(), produto.getQuantidadeEstoque(), quantidadePedido);

            throw new PedidoInvalidoException(
                    "Existem poucas unidades do produto `%s` disponíveis em estoque. Quantidade em estoque: %d"
                            .formatted(produto.getDescricao(), produto.getQuantidadeEstoque())
            );
        }

        produto.setQuantidadeEstoque(produto.getQuantidadeEstoque() - quantidadePedido);
        return new ItemPedido(new ItemPedidoPk(pedido, produto), quantidadePedido);

    }

    private HashMap<Produto, Integer> getQuantidadeProdutoEstoque(List<PedidoProdutoRequest> produtosPedido){

        final var quantidadeProduto = new HashMap<Produto, Integer>();

        final var produtoIds = produtosPedido.stream().map(PedidoProdutoRequest::produtoId).toList();
        final var produtos = produtosRepository.findAllById(produtoIds);

        final var idProdutoTable = new HashMap<Long, Produto>();
        produtos.forEach(produto -> idProdutoTable.put(produto.getId(), produto));

        produtosPedido.forEach(pp -> quantidadeProduto.put(idProdutoTable.get(pp.produtoId()), pp.quantidade()));

        return quantidadeProduto;
    }
}
