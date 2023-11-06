package br.com.poc.ocartaxo.salesorder.mapper.impl;

import br.com.poc.ocartaxo.salesorder.dto.EnderecoRequest;
import br.com.poc.ocartaxo.salesorder.model.Endereco;
import org.springframework.stereotype.Component;

@Component
public class EnderecoMapper {

    public Endereco converte(EnderecoRequest request){
        final var endereco = new Endereco();
        endereco.setLogradouro(request.logradouro());
        endereco.setTipoEndereco(request.tipo());

        return endereco;
    }

}
