package br.com.poc.ocartaxo.salesorder.validacao.impl;

import br.com.poc.ocartaxo.salesorder.infra.exception.DocumentoInvalidoException;
import br.com.poc.ocartaxo.salesorder.validacao.ValidadorDocumento;
import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

@Component
public class ValidadorCNPJ implements ValidadorDocumento {
    private static final Pattern padrao = Pattern.compile("^\\d{2}\\.\\d{3}\\.\\d{3}/\\d{4}-\\d{2}$");

    @Override
    public void verificaDocumento(String cnpj) {
        if(!(padrao.matcher(cnpj).matches())){
            throw new DocumentoInvalidoException("CNPJ com quantidade de caracteres inválida!");
        }
    }
}
