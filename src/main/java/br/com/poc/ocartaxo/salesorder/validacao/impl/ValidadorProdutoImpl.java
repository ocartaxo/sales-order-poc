package br.com.poc.ocartaxo.salesorder.validacao.impl;

import br.com.poc.ocartaxo.salesorder.dto.ProdutoAtualizacaoRequest;
import br.com.poc.ocartaxo.salesorder.dto.ProdutoCadastroRequest;
import br.com.poc.ocartaxo.salesorder.infra.exception.AtualizacaoProdutoException;
import br.com.poc.ocartaxo.salesorder.infra.exception.CadastroProdutoException;
import br.com.poc.ocartaxo.salesorder.validacao.ValidadorProduto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Slf4j
@Component
public class ValidadorProdutoImpl implements ValidadorProduto {
    @Override
    public void validaCadastroProduto(ProdutoCadastroRequest body) {
        log.info("Validando o produto para cadastro={}", body);
        if (body.descricao().isEmpty() || body.quantidadeEstoque() <= 0 || ehPrecoInvalido(body.valor())) {
            throw new CadastroProdutoException("Dados de cadastro do produto inválidos!");
        }

    }

    @Override
    public void validaAtualizacaoProduto(ProdutoAtualizacaoRequest body) {
        if (body.quantidadeEstoque() <= 0 || ehPrecoInvalido(body.valor())) {
            throw new AtualizacaoProdutoException("Dados da atualização do produto inválidos!");
        }
    }

    private boolean ehPrecoInvalido(BigDecimal preco) {
        return preco.compareTo(BigDecimal.ZERO) <= 0;
    }
}
