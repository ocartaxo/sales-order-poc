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

}
