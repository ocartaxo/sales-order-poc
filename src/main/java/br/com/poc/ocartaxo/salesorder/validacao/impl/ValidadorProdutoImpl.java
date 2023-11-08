package br.com.poc.ocartaxo.salesorder.validacao.impl;

import br.com.poc.ocartaxo.salesorder.dto.ProdutoAtualizacaoRequest;
import br.com.poc.ocartaxo.salesorder.dto.ProdutoCadastroRequest;
import br.com.poc.ocartaxo.salesorder.infra.exception.AtualizacaoProdutoException;
import br.com.poc.ocartaxo.salesorder.infra.exception.CadastroProdutoException;
import br.com.poc.ocartaxo.salesorder.validacao.ValidadorProduto;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class ValidadorProdutoImpl implements ValidadorProduto {
    @Override
    public void validaCadastroProduto(ProdutoCadastroRequest body) {

        if (body.descricao().isEmpty() || body.quantidadeEstoque() == 0 || BigDecimal.ZERO.compareTo(body.valor()) == 0
        ) {
            throw new CadastroProdutoException("Dados de cadastro do produto inválidos!");
        }

    }

    @Override
    public void validaAtualizacaoProduto(ProdutoAtualizacaoRequest body) {
        if(body.quantidadeEstoque() == 0 ||   BigDecimal.ZERO.compareTo(body.valor()) == 0){
            throw new AtualizacaoProdutoException("Dados da atualização do produto inválidos!");
        }
    }
}
