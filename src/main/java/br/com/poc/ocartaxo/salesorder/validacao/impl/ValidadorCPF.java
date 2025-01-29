package br.com.poc.ocartaxo.salesorder.validacao.impl;

import br.com.poc.ocartaxo.salesorder.infra.exception.DocumentoInvalidoException;
import br.com.poc.ocartaxo.salesorder.validacao.ValidadorDocumento;
import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

@Component
public class ValidadorCPF implements ValidadorDocumento {
    private static final Pattern padrao = Pattern.compile("^(?:(\\d{3}\\.){2}\\d{3}-\\d{2}|\\d{11})$");
    @Override
    public void verificaDocumento(String cpf) {

        if (!(padrao.matcher(cpf).matches())){
            throw new DocumentoInvalidoException("CPF com quantidade de caracteres inválida!");
        }

    }
}
