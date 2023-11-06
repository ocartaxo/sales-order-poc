package br.com.poc.ocartaxo.salesorder.service;

import br.com.poc.ocartaxo.salesorder.dto.ProdutoCadastroRequest;
import br.com.poc.ocartaxo.salesorder.dto.ProdutoResponse;

public interface ProdutosService {
    ProdutoResponse cadastrarNovoProduto(ProdutoCadastroRequest body);
}
