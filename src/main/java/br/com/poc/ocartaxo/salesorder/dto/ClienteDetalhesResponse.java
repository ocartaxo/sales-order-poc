package br.com.poc.ocartaxo.salesorder.dto;

import java.util.List;

public record ClienteDetalhesResponse(
        Long id,
        String nome,
        String email,
        List<EnderecoResponse> enderecos
) {
}
