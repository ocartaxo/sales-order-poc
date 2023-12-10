package br.com.poc.ocartaxo.salesorder.service;

import br.com.poc.ocartaxo.salesorder.model.Cliente;
import br.com.poc.ocartaxo.salesorder.domain.pedido.EnderecosPedido;

public interface EnderecoClienteService {

    EnderecosPedido getEnderecoEntregaCobranca(Cliente cliente);
}
