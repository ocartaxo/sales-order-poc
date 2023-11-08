package br.com.poc.ocartaxo.salesorder.service.impl;

import br.com.poc.ocartaxo.salesorder.enums.TipoEndereco;
import br.com.poc.ocartaxo.salesorder.infra.exception.CadastroPedidoException;
import br.com.poc.ocartaxo.salesorder.infra.exception.ClienteNaoEncontradoException;
import br.com.poc.ocartaxo.salesorder.model.Cliente;
import br.com.poc.ocartaxo.salesorder.repository.ClientesRepository;
import br.com.poc.ocartaxo.salesorder.service.PedidoClienteService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class PedidoClienteServiceImpl implements PedidoClienteService {

    private final ClientesRepository clientesRepository;
    @Override
    public Cliente buscaClientePorId(Long id) {

        final var cliente = clientesRepository.findById(id);

        if(cliente.isEmpty()){
            throw new ClienteNaoEncontradoException("O cliente de id `%d` não está cadastrado!".formatted(id));
        }

        return cliente.get();
    }

    @Override
    public EnderecosPedido getEnderecoEntregaCobranca(Cliente cliente) {

        final var enderecos = cliente.getEnderecos();
        log.info("Enderecos={}", enderecos);

        final var enderecoAll = enderecos.stream().filter(e-> e.getTipoEndereco() == TipoEndereco.ALL).findFirst();
        if (enderecoAll.isPresent()){
            return new EnderecosPedido(enderecoAll.get().getLogradouro(), enderecoAll.get().getLogradouro());
        }

        final var enderecoCobranca = enderecos.stream().filter( e -> e.getTipoEndereco() == TipoEndereco.COBRANCA).findFirst();
        final var enderecoEntrega = enderecos.stream().filter(e -> TipoEndereco.ENTREGA.equals(e.getTipoEndereco())).findFirst();

        if (enderecoEntrega.isEmpty() || enderecoCobranca.isEmpty()){
            throw new CadastroPedidoException("Não foi possível definir os enderecos de entrega e cobranca do pedido! do cliente %s".formatted(cliente.getNome()));
        }

        return new EnderecosPedido(enderecoEntrega.get().getLogradouro(), enderecoCobranca.get().getLogradouro());

    }
}
