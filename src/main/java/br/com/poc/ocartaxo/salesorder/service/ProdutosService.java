package br.com.poc.ocartaxo.salesorder.service;

import br.com.poc.ocartaxo.salesorder.dto.ProdutoAtualizacaoRequest;
import br.com.poc.ocartaxo.salesorder.dto.ProdutoCadastroRequest;
import br.com.poc.ocartaxo.salesorder.dto.ProdutoResponse;
import br.com.poc.ocartaxo.salesorder.dto.ProdutoResumidoResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProdutosService {
    ProdutoResponse cadastrarNovoProduto(ProdutoCadastroRequest body);

    ProdutoResponse buscarProduto(Long id);

    Page<ProdutoResumidoResponse> listarTodosProdutos(Pageable pageable);

    ProdutoResponse atualizarProduto(Long id, ProdutoAtualizacaoRequest body);

    void deletarProduto(Long id);
}
