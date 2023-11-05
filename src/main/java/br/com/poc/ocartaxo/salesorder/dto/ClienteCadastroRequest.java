package br.com.poc.ocartaxo.salesorder.dto;

import java.util.List;

public record ClienteCadastroRequest(
        String nome,
        String email,
        DocumentoRequest documento,
        List<EnderecoRequest> enderecos
) {
}
