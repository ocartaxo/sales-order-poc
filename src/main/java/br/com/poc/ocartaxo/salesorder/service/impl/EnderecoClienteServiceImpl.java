package br.com.poc.ocartaxo.salesorder.service.impl;

import br.com.poc.ocartaxo.salesorder.domain.pedido.EnderecosPedido;
import br.com.poc.ocartaxo.salesorder.enums.TipoEndereco;
import br.com.poc.ocartaxo.salesorder.infra.exception.CadastroPedidoException;
import br.com.poc.ocartaxo.salesorder.model.Cliente;
import br.com.poc.ocartaxo.salesorder.service.EnderecoClienteService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class EnderecoClienteServiceImpl implements EnderecoClienteService {

    @Override
    public EnderecosPedido getEnderecoEntregaCobranca(Cliente cliente) {

        final var enderecos = cliente.getEnderecos();
        log.info("Enderecos={}", enderecos);

        final var enderecoAll = enderecos.stream().filter(e-> e.getTipoEndereco() == TipoEndereco.ALL).findFirst();
        if (enderecoAll.isPresent()){
            return new EnderecosPedido(enderecoAll.get().getLogradouro(), enderecoAll.get().getLogradouro());
        }

        final var enderecoCobranca = enderecos.stream()
                .filter( e -> TipoEndereco.COBRANCA.equals(e.getTipoEndereco()))
                .findFirst()
                .orElseThrow(() ->
                        new CadastroPedidoException("Endereço de cobrança não encontrado")
                );

        final var enderecoEntrega = enderecos.stream()
                .filter(e -> TipoEndereco.ENTREGA.equals(e.getTipoEndereco()))
                .findFirst()
                .orElseThrow(() ->
                        new CadastroPedidoException("Endereço de entrega não encontrado")
                );

        return new EnderecosPedido(enderecoEntrega.getLogradouro(), enderecoCobranca.getLogradouro());

    }
}
