package br.com.poc.ocartaxo.salesorder.infra.exception;

import br.com.poc.ocartaxo.salesorder.model.Produto;

public class QuantidadeProdutoInsuficienteException extends RuntimeException {
    public QuantidadeProdutoInsuficienteException(Produto produto) {
        super("Existem poucas unidades do produto `%s` disponíveis em estoque. Quantidade em estoque: %d"
                .formatted(produto.getDescricao(), produto.getQuantidadeEstoque()));
    }
}
