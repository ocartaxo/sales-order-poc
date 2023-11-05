package br.com.poc.ocartaxo.salesorder.mapper;

import br.com.poc.ocartaxo.salesorder.dto.CadastroClienteRequest;
import br.com.poc.ocartaxo.salesorder.dto.ClienteResponse;
import br.com.poc.ocartaxo.salesorder.model.Cliente;

public interface ClienteMapper {

    Cliente converteParaEntidade(CadastroClienteRequest request);

    ClienteResponse converteParaDTO(Cliente entidade);

}
