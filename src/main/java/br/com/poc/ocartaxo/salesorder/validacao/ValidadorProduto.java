package br.com.poc.ocartaxo.salesorder.validacao;

import br.com.poc.ocartaxo.salesorder.dto.ProdutoAtualizacaoRequest;
import br.com.poc.ocartaxo.salesorder.dto.ProdutoCadastroRequest;

public interface ValidadorProduto {

    void validaCadastroProduto(ProdutoCadastroRequest body);

    void validaAtualizacaoProduto(ProdutoAtualizacaoRequest body);
}
