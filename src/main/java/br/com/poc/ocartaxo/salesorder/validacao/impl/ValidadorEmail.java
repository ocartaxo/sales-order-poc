package br.com.poc.ocartaxo.salesorder.validacao.impl;

import br.com.poc.ocartaxo.salesorder.infra.exception.InformacoesClienteInvalidaException;
import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

@Component
public class ValidadorEmail {

    private final Pattern PADRAO = Pattern.compile("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$");

    /**
     * Verifica se é um e-mail válido
     * @param email E-mail do cliente
     * @throws InformacoesClienteInvalidaException Informa que é um e-mail inválido
     */
    public void valida(String email) {
        if (email.isBlank() || !(PADRAO.matcher(email).find())) {
            throw new InformacoesClienteInvalidaException("Email `%s` inválido!".formatted(email));
        }
    }

}
