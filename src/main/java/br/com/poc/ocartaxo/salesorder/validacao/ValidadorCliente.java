package br.com.poc.ocartaxo.salesorder.validacao;

import br.com.poc.ocartaxo.salesorder.dto.ClienteAtualizacaoRequest;
import br.com.poc.ocartaxo.salesorder.dto.ClienteCadastroRequest;

public interface ValidadorCliente {

    void validaDadosCadastroCliente(ClienteCadastroRequest request);
    void validaDadosAtualizacaoCliente(ClienteAtualizacaoRequest body);
}
