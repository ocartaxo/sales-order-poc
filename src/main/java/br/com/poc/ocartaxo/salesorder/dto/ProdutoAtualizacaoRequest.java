package br.com.poc.ocartaxo.salesorder.dto;

import java.math.BigDecimal;

public record ProdutoAtualizacaoRequest(
        BigDecimal valor,
        int quantidadeEstoque
) {
}
