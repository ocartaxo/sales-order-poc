package br.com.poc.ocartaxo.salesorder.dto;

import br.com.poc.ocartaxo.salesorder.enums.CategoriaProduto;

import java.math.BigDecimal;

public record ProdutoCadastroRequest(
        String descricao,
        CategoriaProduto categoria,
        BigDecimal valor,
        int quantidadeEstoque
) {
}
