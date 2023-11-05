package br.com.poc.ocartaxo.salesorder.service.impl;

import br.com.poc.ocartaxo.salesorder.dto.CadastroClienteRequest;
import br.com.poc.ocartaxo.salesorder.dto.ClienteResponse;
import br.com.poc.ocartaxo.salesorder.mapper.ClienteMapper;
import br.com.poc.ocartaxo.salesorder.repository.ClientesRepository;
import br.com.poc.ocartaxo.salesorder.service.ClientesService;
import br.com.poc.ocartaxo.salesorder.validacao.ValidadorCliente;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ClientesServiceImpl implements ClientesService {

    private final ClienteMapper mapper;
    private final ClientesRepository repository;
    private final ValidadorCliente validadorCliente;


    @Override
    public ClienteResponse cadastraNovoCliente(CadastroClienteRequest request) {
        log.info("Cadastrando o cliente %s".formatted(request.nome()));

        validadorCliente.validaCadastroCliente(request);

        final var entidade = mapper.converteParaEntidade(request);

        repository.save(entidade);

        return mapper.converteParaDTO(entidade);
    }
}
