package br.com.poc.ocartaxo.salesorder.service.impl;

import br.com.poc.ocartaxo.salesorder.dto.PedidoAtualizacaoRequest;
import br.com.poc.ocartaxo.salesorder.dto.PedidoCadastroRequest;
import br.com.poc.ocartaxo.salesorder.dto.PedidoDetalhesResponse;
import br.com.poc.ocartaxo.salesorder.dto.PedidoResponse;
import br.com.poc.ocartaxo.salesorder.infra.exception.PedidoNaoEncontradoException;
import br.com.poc.ocartaxo.salesorder.mapper.PedidoMapper;
import br.com.poc.ocartaxo.salesorder.repository.PedidosRepository;
import br.com.poc.ocartaxo.salesorder.service.PedidosService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class PedidosServiceImpl implements PedidosService {

    private final PedidosRepository repository;
    private final PedidoMapper mapper;

    @Override
    @Transactional
    public PedidoResponse cadastraNovoPedido(PedidoCadastroRequest body) {

        final var pedido = mapper.converteParaEntidade(body);

        repository.save(pedido);

        return mapper.converteParaDTO(pedido);
    }

    @Override
    public PedidoDetalhesResponse buscarPorId(Long id) {

        final var op = repository.findById(id);
        if(op.isEmpty()){
            throw new PedidoNaoEncontradoException("O pedido de id `%d` não foi encontrado!".formatted(id));
        }

        return mapper.converteParaDetalhesDTO(op.get());
    }

    @Override
    public Page<PedidoResponse> listarTodosPedidos(Pageable pageable) {
        return repository.findAll(pageable).map(mapper::converteParaDTO);
    }

    @Override
    @Transactional
    public PedidoDetalhesResponse atualizarPedido(Long id, PedidoAtualizacaoRequest body) {
        final var op = repository.findById(id);
        if(op.isEmpty()){
            throw new PedidoNaoEncontradoException("O pedido de id `%d` não foi encontrado!".formatted(id));
        }

        final var pedido = op.get();
        pedido.setStatus(body.status());

        return mapper.converteParaDetalhesDTO(pedido);
    }

    @Override
    public void deletarPorId(Long id) {
        repository.deleteById(id);
    }
}
