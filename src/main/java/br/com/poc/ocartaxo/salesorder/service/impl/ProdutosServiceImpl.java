package br.com.poc.ocartaxo.salesorder.service.impl;

import br.com.poc.ocartaxo.salesorder.dto.ProdutoAtualizacaoRequest;
import br.com.poc.ocartaxo.salesorder.dto.ProdutoCadastroRequest;
import br.com.poc.ocartaxo.salesorder.dto.ProdutoResponse;
import br.com.poc.ocartaxo.salesorder.infra.exception.ProdutoNaoEncontradoException;
import br.com.poc.ocartaxo.salesorder.mapper.ProdutoMapper;
import br.com.poc.ocartaxo.salesorder.repository.ProdutosRepository;
import br.com.poc.ocartaxo.salesorder.service.ProdutosService;
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

    @Override
    public ProdutoResponse cadastrarNovoProduto(ProdutoCadastroRequest body) {
        log.info("Cadastrando o produto `%s`".formatted(body.descricao()));
        final var produto = mapper.converteParaEntidade(body);

        repository.save(produto);

        return mapper.converteParaDto(produto);
    }

    @Override
    public ProdutoResponse buscarProduto(Long id) {
        log.info("Buscando o produto de id %d".formatted(id));
        final var op = repository.findById(id);

        if (op.isEmpty()){
            throw new ProdutoNaoEncontradoException("Produto de id `%d` não cadastrado!".formatted(id));
        }

        return mapper.converteParaDto(op.get());
    }

    @Override
    public Page<ProdutoResponse> listarTodosProdutos(Pageable pageable) {
        log.info("Listando os %d produtos da página %d".formatted(pageable.getPageSize(), pageable.getPageNumber()));
        return repository.findAll(pageable).map(mapper::converteParaDto);
    }

    @Override
    public ProdutoResponse atualizarProduto(Long id, ProdutoAtualizacaoRequest body) {
        log.info("Atualizando o produto de id %d".formatted(id));
        final var op = repository.findById(id);

        if (op.isEmpty()){
            throw new ProdutoNaoEncontradoException("Produto de id `%d` não cadastrado!".formatted(id));
        }

        final var produto = op.get();

        produto.atualiza(body);

        return mapper.converteParaDto(produto);
    }

    @Override
    public void deletarProduto(Long id) {
        repository.deleteById(id);
    }
}
