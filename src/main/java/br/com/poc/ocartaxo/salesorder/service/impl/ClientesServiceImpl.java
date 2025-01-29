package br.com.poc.ocartaxo.salesorder.service.impl;

import br.com.poc.ocartaxo.salesorder.dto.ClienteAtualizacaoRequest;
import br.com.poc.ocartaxo.salesorder.dto.ClienteCadastroRequest;
import br.com.poc.ocartaxo.salesorder.dto.ClienteResponse;
import br.com.poc.ocartaxo.salesorder.infra.exception.ClienteNaoEncontradoException;
import br.com.poc.ocartaxo.salesorder.mapper.ClienteMapper;
import br.com.poc.ocartaxo.salesorder.model.Cliente;
import br.com.poc.ocartaxo.salesorder.repository.ClientesRepository;
import br.com.poc.ocartaxo.salesorder.service.ClientesService;
import br.com.poc.ocartaxo.salesorder.validacao.ValidadorCliente;
import jakarta.transaction.Transactional;
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

    /***
     * Cadastra um novo cliente
     * @param body Informações necessárias para cadastrar um novo cliente
     * @return Informações de um cliente
     */

    @Override
    public ClienteResponse cadastrarNovoCliente(ClienteCadastroRequest body) {
        log.info("Cadastrando o cliente %s".formatted(body.nome()));

        validadorCliente.validaDadosCadastroCliente(body);

        final var entidade = mapper.converteParaEntidade(body);

        repository.save(entidade);

        return mapper.converteParaDTO(entidade);
    }

    /**
     * Busca por clientes de forma páginada
     * @param pageable Filtros aplicados na busca do cliente
     * @return Uma página contendo uma lista de clientes
     */

    @Override
    public Page<ClienteResponse> buscarTodosClientes(Pageable pageable) {
        log.info("Listando %d clientes ná página %d".formatted(pageable.getPageSize(), pageable.getPageNumber()));
        return repository.findAll(pageable).map(mapper::converteParaDTO);
    }

    /**
     * Busca um cliente de determinado id
     * @param id Id do cliente que deseja encontrar
     * @return Informações de um cliente
     * @throws ClienteNaoEncontradoException exceção para cliente não cadastrado
     */
    @Override
    public ClienteResponse buscarClientePorId(Long id) {

        Cliente cliente = repository.findById(id).orElseThrow(() ->
                new ClienteNaoEncontradoException("O cliente de id `%d` não está cadastrado!".formatted(id))
        );

        return mapper.converteParaDTO(cliente);
    }

    /**
     * Atualiza as informaçõs de um cliente de id correspondete
     * @param id id do cliente do cliente que deseja atualizar
     * @param body informações que devem ser atualizadas
     * @return Informações atualizadas do cleinte
     * @throws ClienteNaoEncontradoException exceção para cliente não cadastrado
     */

    @Override
    @Transactional
    public ClienteResponse atualizarCliente(Long id, ClienteAtualizacaoRequest body) {

        Cliente cliente = repository.findById(id).orElseThrow(() ->
                new ClienteNaoEncontradoException("O cliente de id `%d` não está cadastrado!".formatted(id))
        );

        validadorCliente.validaDadosAtualizacaoCliente(body);

        cliente.atualiza(body);

        return mapper.converteParaDTO(cliente);
    }

    /**
     * Deleta um cliente de id correspondente
     * @param id Id do cliente que desjea deletar
     */
    @Override
    public void deletarClientePorId(Long id) {
        repository.deleteById(id);
    }

}
