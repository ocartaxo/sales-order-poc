package br.com.poc.ocartaxo.salesorder.mapper;

import br.com.poc.ocartaxo.salesorder.dto.ClienteCadastroRequest;
import br.com.poc.ocartaxo.salesorder.dto.ClienteResponse;
import br.com.poc.ocartaxo.salesorder.model.Cliente;

public interface ClienteMapper {

    Cliente converteParaEntidade(ClienteCadastroRequest request);

    ClienteResponse converteParaDTO(Cliente entidade);

}
