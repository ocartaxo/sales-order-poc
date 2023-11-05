package br.com.poc.ocartaxo.salesorder.dto;

import br.com.poc.ocartaxo.salesorder.enums.TipoEndereco;

public record EnderecoRequest(
        String logradouro,
        TipoEndereco tipo

) {
}
