package br.com.poc.ocartaxo.salesorder.mapper.impl;

import br.com.poc.ocartaxo.salesorder.domain.pedido.EnderecosPedido;
import br.com.poc.ocartaxo.salesorder.dto.EnderecoRequest;
import br.com.poc.ocartaxo.salesorder.dto.EnderecoResponse;
import br.com.poc.ocartaxo.salesorder.enums.TipoEndereco;
import br.com.poc.ocartaxo.salesorder.infra.exception.CadastroPedidoException;
import br.com.poc.ocartaxo.salesorder.model.Endereco;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
public class EnderecoMapper {

    public Endereco converte(EnderecoRequest request) {
        log.info("Endereco Body Request={}", request);
        final var endereco = new Endereco();
        endereco.setLogradouro(request.logradouro());
        endereco.setTipoEndereco(request.tipo());

        return endereco;
    }

    public EnderecoResponse converte(Endereco response) {
        return new EnderecoResponse(response.getLogradouro(), response.getTipoEndereco());
    }

    public EnderecosPedido converteEnderecosPedido(List<Endereco> enderecos) {
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
