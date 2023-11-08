package br.com.poc.ocartaxo.salesorder.service;

import br.com.poc.ocartaxo.salesorder.model.Cliente;
import br.com.poc.ocartaxo.salesorder.model.Produto;

import java.util.List;
import java.util.Set;

public interface PedidoProdutoService {
    Set<Produto> buscaProdutosPorId(List<Long> produtosId);
}
