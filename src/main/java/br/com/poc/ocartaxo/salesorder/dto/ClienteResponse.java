package br.com.poc.ocartaxo.salesorder.dto;

public record ClienteResponse(
        Long id,
        String nome,
        String email
) {
}
