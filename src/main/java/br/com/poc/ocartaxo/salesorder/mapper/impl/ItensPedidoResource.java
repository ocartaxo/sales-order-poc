package br.com.poc.ocartaxo.salesorder.mapper.impl;

import br.com.poc.ocartaxo.salesorder.model.Produto;

import java.math.BigDecimal;

public record ItensPedidoResource(
        int quantidade,
        BigDecimal valor
) {
}
