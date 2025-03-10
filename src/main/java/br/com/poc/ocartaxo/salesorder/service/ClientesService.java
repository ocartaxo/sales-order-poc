package br.com.poc.ocartaxo.salesorder.service;

import br.com.poc.ocartaxo.salesorder.dto.ClienteAtualizacaoRequest;
import br.com.poc.ocartaxo.salesorder.dto.ClienteCadastroRequest;
import br.com.poc.ocartaxo.salesorder.dto.ClienteResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ClientesService {

    ClienteResponse cadastrarNovoCliente(ClienteCadastroRequest request);

    Page<ClienteResponse> buscarTodosClientes(Pageable pageable);

    ClienteResponse buscarClientePorId(Long id);

    ClienteResponse atualizarCliente(Long id, ClienteAtualizacaoRequest body);

    void deletarClientePorId(Long id);
}
