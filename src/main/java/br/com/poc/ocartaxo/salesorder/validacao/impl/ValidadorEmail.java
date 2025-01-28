package br.com.poc.ocartaxo.salesorder.validacao.impl;

import br.com.poc.ocartaxo.salesorder.infra.exception.EmailInvalidoException;
import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

@Component
public class ValidadorEmail {

    private final Pattern padrao = Pattern.compile("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$");

    public void valida(String email) {
        if (email.isEmpty() || !(padrao.matcher(email).find())) {
            throw new EmailInvalidoException("Email `%s` inválido!".formatted(email));
        }
    }

}
