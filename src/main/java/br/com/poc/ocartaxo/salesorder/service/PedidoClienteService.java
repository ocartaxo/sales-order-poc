package br.com.poc.ocartaxo.salesorder.service;

import br.com.poc.ocartaxo.salesorder.model.Cliente;
import br.com.poc.ocartaxo.salesorder.service.impl.EnderecosPedido;

public interface PedidoClienteService {
    Cliente buscaClientePorId(Long id);

    EnderecosPedido getEnderecoEntregaCobranca(Cliente cliente);
}
