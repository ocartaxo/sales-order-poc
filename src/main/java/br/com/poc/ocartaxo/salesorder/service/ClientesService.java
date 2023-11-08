package br.com.poc.ocartaxo.salesorder.service;

import br.com.poc.ocartaxo.salesorder.dto.ClienteAtualizacaoRequest;
import br.com.poc.ocartaxo.salesorder.dto.ClienteCadastroRequest;
import br.com.poc.ocartaxo.salesorder.dto.ClienteDetalhesResponse;
import br.com.poc.ocartaxo.salesorder.dto.ClienteResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ClientesService {

    ClienteDetalhesResponse cadastrarNovoCliente(ClienteCadastroRequest request);

    Page<ClienteResponse> buscarTodosClientes(Pageable pageable);

    ClienteDetalhesResponse buscarClientePorId(Long id);

    ClienteDetalhesResponse atualizarCliente(Long id, ClienteAtualizacaoRequest body);

    void deletarClientePorId(Long id);
}
