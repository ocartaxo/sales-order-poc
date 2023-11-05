package br.com.poc.ocartaxo.salesorder.service.impl;

import br.com.poc.ocartaxo.salesorder.dto.ClienteCadastroRequest;
import br.com.poc.ocartaxo.salesorder.dto.ClienteResponse;
import br.com.poc.ocartaxo.salesorder.infra.exception.ClienteNaoEncontradoException;
import br.com.poc.ocartaxo.salesorder.mapper.ClienteMapper;
import br.com.poc.ocartaxo.salesorder.repository.ClientesRepository;
import br.com.poc.ocartaxo.salesorder.service.ClientesService;
import br.com.poc.ocartaxo.salesorder.validacao.ValidadorCliente;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ClientesServiceImpl implements ClientesService {

    private final ClienteMapper mapper;
    private final ClientesRepository repository;
    private final ValidadorCliente validadorCliente;


    @Override
    public ClienteResponse cadastrarNovoCliente(ClienteCadastroRequest body) {
        log.info("Cadastrando o cliente %s".formatted(body.nome()));

        validadorCliente.validaCadastroCliente(body);

        final var entidade = mapper.converteParaEntidade(body);

        repository.save(entidade);

        return mapper.converteParaDTO(entidade);
    }

    @Override
    public Page<ClienteResponse> buscarTodosClientes(Pageable pageable) {
        log.info("Listando %d clientes ná página %d".formatted(pageable.getPageSize(), pageable.getPageNumber()));
        return repository.findAll(pageable).map(mapper::converteParaDTO);
    }

    @Override
    public ClienteResponse buscarClientePorId(Long id) {

        final var cliente = repository.findById(id);

        if(cliente.isEmpty()){
            throw new ClienteNaoEncontradoException("O cliente de id `%d` não está cadastrado!".formatted(id));
        }

        return mapper.converteParaDTO(cliente.get());
    }


}
