package br.com.poc.ocartaxo.salesorder.mapper.impl;

import br.com.poc.ocartaxo.salesorder.dto.EnderecoRequest;
import br.com.poc.ocartaxo.salesorder.model.Endereco;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class EnderecoMapper {

    public Endereco converte(EnderecoRequest request){
        log.info("Endereco Body Request={}", request);
        final var endereco = new Endereco();
        endereco.setLogradouro(request.logradouro());
        endereco.setTipoEndereco(request.tipo());

        return endereco;
    }

}
