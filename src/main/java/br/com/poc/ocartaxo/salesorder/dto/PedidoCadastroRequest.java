package br.com.poc.ocartaxo.salesorder.dto;

import java.util.List;

public record PedidoCadastroRequest(
        Long clienteId,
        List<PedidoProdutoRequest> produtos
) {
}
