package br.com.poc.ocartaxo.salesorder.service;

import br.com.poc.ocartaxo.salesorder.dto.ClienteCadastroRequest;
import br.com.poc.ocartaxo.salesorder.dto.ClienteResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ClientesService {

    ClienteResponse cadastraNovoCliente(ClienteCadastroRequest request);

    Page<ClienteResponse> buscarTodosClientes(Pageable pageable);
}
