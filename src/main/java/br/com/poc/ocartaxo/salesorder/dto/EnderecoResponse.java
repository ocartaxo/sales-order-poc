package br.com.poc.ocartaxo.salesorder.dto;

import br.com.poc.ocartaxo.salesorder.enums.TipoEndereco;

public record EnderecoResponse(
        String logradouro,
        TipoEndereco tipo
) {
}
