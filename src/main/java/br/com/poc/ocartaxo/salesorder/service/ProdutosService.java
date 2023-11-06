package br.com.poc.ocartaxo.salesorder.service;

import br.com.poc.ocartaxo.salesorder.dto.ProdutoCadastroRequest;
import br.com.poc.ocartaxo.salesorder.dto.ProdutoResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProdutosService {
    ProdutoResponse cadastrarNovoProduto(ProdutoCadastroRequest body);

    ProdutoResponse buscarProduto(Long id);

    Page<ProdutoResponse> listarTodosProdutos(Pageable pageable);
}
