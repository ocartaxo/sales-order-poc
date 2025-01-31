package br.com.poc.ocartaxo.salesorder.service.impl;

import br.com.poc.ocartaxo.salesorder.dto.ProdutoAtualizacaoRequest;
import br.com.poc.ocartaxo.salesorder.dto.ProdutoCadastroRequest;
import br.com.poc.ocartaxo.salesorder.dto.ProdutoResponse;
import br.com.poc.ocartaxo.salesorder.infra.exception.ProdutoNaoEncontradoException;
import br.com.poc.ocartaxo.salesorder.mapper.ProdutoMapper;
import br.com.poc.ocartaxo.salesorder.repository.ProdutosRepository;
import br.com.poc.ocartaxo.salesorder.service.ProdutosService;
import br.com.poc.ocartaxo.salesorder.validacao.ValidadorProduto;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProdutosServiceImpl implements ProdutosService {

    private final ProdutosRepository repository;
    private final ProdutoMapper mapper;
    private final ValidadorProduto validador;

    /**
     * Cadastra um novo produto na base
     * @param body Inforamções do novo produto
     * @return Informações básicas de um produto
     */
    @Override
    @Transactional
    public ProdutoResponse cadastrarNovoProduto(ProdutoCadastroRequest body) {

        validador.validaCadastroProduto(body);

        log.info("Cadastrando o produto `%s`".formatted(body.descricao()));

        var produto = mapper.converteParaEntidade(body);
        produto = repository.save(produto);

        return mapper.converteParaDto(produto);
    }

    /**
     * Busca um produto que corresponda ao id informado
     * @param id Id do produto
     * @return Informações do prodto
     */
    @Override
    public ProdutoResponse buscarProduto(Long id) {
        log.info("Buscando o produto de id %d".formatted(id));
        final var produto = repository.findById(id).orElseThrow(() ->
                new ProdutoNaoEncontradoException("Produto de id `%d` não cadastrado!".formatted(id))
        );


        return mapper.converteParaDto(produto);
    }

    /**
     * Lista informações dos produtos que correspondam ao filtro informado
     * @param pageable Filtro utilziado na busca
     * @return Página contendo informações do produto
     */
    @Override
    public Page<ProdutoResponse> listarTodosProdutos(Pageable pageable) {
        log.info("Listando os %d produtos da página %d".formatted(pageable.getPageSize(), pageable.getPageNumber()));
        return repository.findAll(pageable).map(mapper::converteParaDto);
    }

    /**
     * Atualiza Informações do produto que corresponda ao id informado
     * @param id Id do produto que deseja atualizar
     * @param body Informações que serão atualizadas do produto
     * @return Produto com as informações atualizadas
     * @throws ProdutoNaoEncontradoException
     */
    @Override
    @Transactional
    public ProdutoResponse atualizarProduto(Long id, ProdutoAtualizacaoRequest body) {
        validador.validaAtualizacaoProduto(body);

        log.info("Atualizando o produto de id %d".formatted(id));

        final var produto = repository.findById(id).orElseThrow(() ->
                new ProdutoNaoEncontradoException("Produto de id `%d` não cadastrado!".formatted(id))
        );

        produto.atualiza(body);

        return mapper.converteParaDto(produto);
    }

    /**
     * Remove um produto que corresponda ao id informado
     * @param id Id do produto que deseja remover
     */
    @Override
    public void deletarProduto(Long id) {
        repository.deleteById(id);
    }
}
