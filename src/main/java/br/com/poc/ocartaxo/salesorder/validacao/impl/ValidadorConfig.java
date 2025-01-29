package br.com.poc.ocartaxo.salesorder.validacao.impl;

import br.com.poc.ocartaxo.salesorder.enums.TipoDocumento;
import br.com.poc.ocartaxo.salesorder.validacao.ValidadorDocumento;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Configuration
public class ValidadorConfig {

    @Bean
    public Map<TipoDocumento, ValidadorDocumento> validadorMap() {

        return Map.of(
                TipoDocumento.CNPJ, new ValidadorCNPJ(),
                TipoDocumento.CPF, new ValidadorCPF()
        );
    }
}
