package br.com.poc.ocartaxo.salesorder.service;

import br.com.poc.ocartaxo.salesorder.dto.CadastroClienteRequest;
import br.com.poc.ocartaxo.salesorder.dto.ClienteResponse;

public interface ClientesService {

    ClienteResponse cadastraNovoCliente(CadastroClienteRequest request);
}
