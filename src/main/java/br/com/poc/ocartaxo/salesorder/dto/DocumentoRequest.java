package br.com.poc.ocartaxo.salesorder.dto;

import br.com.poc.ocartaxo.salesorder.enums.TipoDocumento;

public record DocumentoRequest(
        String digitos,
        TipoDocumento tipo
) {
}
