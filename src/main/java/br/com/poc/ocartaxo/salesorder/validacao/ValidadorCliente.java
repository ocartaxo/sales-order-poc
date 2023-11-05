package br.com.poc.ocartaxo.salesorder.validacao;

import br.com.poc.ocartaxo.salesorder.dto.ClienteCadastroRequest;

public interface ValidadorCliente {

    void validaCadastroCliente(ClienteCadastroRequest request);
}
