package br.com.poc.ocartaxo.salesorder.service.impl;

import br.com.poc.ocartaxo.salesorder.model.Cliente;
import br.com.poc.ocartaxo.salesorder.model.Produto;
import br.com.poc.ocartaxo.salesorder.repository.ProdutosRepository;
import br.com.poc.ocartaxo.salesorder.service.PedidoProdutoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class PedidoProdutoServiceImpl implements PedidoProdutoService {
    private final ProdutosRepository repository;

    @Override
    public Set<Produto> buscaProdutosPorId(List<Long> produtosId) {
        log.info("Buscando produtos do pedido");
        return new HashSet<>(repository.findAllById(produtosId));
    }
}
