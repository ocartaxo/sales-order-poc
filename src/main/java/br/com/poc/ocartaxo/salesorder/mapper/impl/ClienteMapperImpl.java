package br.com.poc.ocartaxo.salesorder.mapper.impl;

import br.com.poc.ocartaxo.salesorder.domain.pedido.EnderecosPedido;
import br.com.poc.ocartaxo.salesorder.dto.ClienteCadastroRequest;
import br.com.poc.ocartaxo.salesorder.dto.ClienteResponse;
import br.com.poc.ocartaxo.salesorder.mapper.ClienteMapper;
import br.com.poc.ocartaxo.salesorder.model.Cliente;
import br.com.poc.ocartaxo.salesorder.model.Endereco;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class ClienteMapperImpl implements ClienteMapper {

    private final EnderecoMapper enderecoMapper;
    private final DocumentoMapper documentoMapper;

    @Override
    public Cliente converteParaEntidade(ClienteCadastroRequest request) {
        log.info("Mapeando `%s` para `%s`".formatted(ClienteCadastroRequest.class.getName(), Cliente.class.getName()));
        final var cliente = new Cliente();

        cliente.setNome(request.nome());
        cliente.setEmail(request.email());
        cliente.setDocumento(documentoMapper.converte(request.documento()));
        cliente.setEnderecos(request.enderecos().stream().map(enderecoMapper::converte).toList());

        return cliente;
    }

    @Override
    public ClienteResponse converteParaDTO(Cliente entidade) {
        return new ClienteResponse(entidade.getId(), entidade.getNome(), entidade.getEmail());
    }

}
