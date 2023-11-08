package br.com.poc.ocartaxo.salesorder.produto;

import br.com.poc.ocartaxo.salesorder.dto.ProdutoCadastroRequest;
import br.com.poc.ocartaxo.salesorder.enums.CategoriaProduto;
import br.com.poc.ocartaxo.salesorder.mapper.ProdutoMapper;
import br.com.poc.ocartaxo.salesorder.mapper.impl.ProdutoMapperImpl;
import br.com.poc.ocartaxo.salesorder.model.Produto;
import br.com.poc.ocartaxo.salesorder.validacao.ValidadorProduto;
import br.com.poc.ocartaxo.salesorder.validacao.impl.ValidadorProdutoImpl;

import java.math.BigDecimal;

public class ProdutoFixture {

    public static Produto produto() {
        return new Produto(
                0L,
                "Smartphone Galaxy S22",
                CategoriaProduto.CELULAR,
                new BigDecimal("1999.99"),
                50
        );
    }

    public static ProdutoCadastroRequest cadastroProdutoValido() {
        return new ProdutoCadastroRequest(
                "Smartphone Galaxy S22",
                CategoriaProduto.CELULAR,
                new BigDecimal("1999.99"),
                50
        );
    }

    public static ProdutoCadastroRequest cadastroProdutoDescricaoInvalida() {
        return new ProdutoCadastroRequest(
                "",
                CategoriaProduto.CELULAR,
                new BigDecimal("1999.99"),
                50
        );
    }

    public static ProdutoCadastroRequest cadastroProdutoPrecoInvalidoIgualAZero() {
        return new ProdutoCadastroRequest(
                "Smartphone Galaxy S22",
                CategoriaProduto.CELULAR,
                BigDecimal.ZERO,
                50
        );
    }

    public static ProdutoCadastroRequest cadastroProdutoPrecoInvalidoMenorQueZero() {
        return new ProdutoCadastroRequest(
                "Smartphone Galaxy S22",
                CategoriaProduto.CELULAR,
                new BigDecimal("-1999.99"),
                50
        );
    }

    public static ProdutoCadastroRequest cadastroProdutoQuantidadeEstoqueIgualAZero() {
        return new ProdutoCadastroRequest(
                "Smartphone Galaxy S22",
                CategoriaProduto.CELULAR,
                new BigDecimal("1999.99"),
                0
        );
    }

    public static ProdutoCadastroRequest cadastroProdutoQuantidadeEstoqueMenorQueZero() {
        return new ProdutoCadastroRequest(
                "Smartphone Galaxy S22",
                CategoriaProduto.CELULAR,
                new BigDecimal("1999.99"),
                -2
        );
    }

    public static ValidadorProduto validadorProduto(){
        return new ValidadorProdutoImpl();
    }

    public static ProdutoMapper mapper(){
        return new ProdutoMapperImpl();
    }

}
