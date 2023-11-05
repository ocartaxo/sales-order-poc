package br.com.poc.ocartaxo.salesorder.validacao;

import br.com.poc.ocartaxo.salesorder.dto.CadastroClienteRequest;

public interface ValidadorCliente {

    void validaCadastroCliente(CadastroClienteRequest request);
}
