package br.com.poc.ocartaxo.salesorder.dto;

import java.util.List;

public record ClienteAtualizacaoRequest(
        String nome,
        String email
) {
}
