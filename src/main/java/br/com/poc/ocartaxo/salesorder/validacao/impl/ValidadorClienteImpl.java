package br.com.poc.ocartaxo.salesorder.validacao.impl;

import br.com.poc.ocartaxo.salesorder.dto.ClienteCadastroRequest;
import br.com.poc.ocartaxo.salesorder.enums.TipoDocumento;
import br.com.poc.ocartaxo.salesorder.infra.exception.CadastroClienteException;
import br.com.poc.ocartaxo.salesorder.validacao.ValidadorCliente;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Map;

@Slf4j
@Component
@RequiredArgsConstructor
public class ValidadorClienteImpl implements ValidadorCliente {

    private final Map<TipoDocumento, ValidadorDocumento> validadorDocumentos;
    private final ValidadorEmail validadorEmail;


    @Override
    public void validaCadastroCliente(ClienteCadastroRequest request) {
        log.info("Validando o cliente `%s`".formatted(request.nome()));

        validadorEmail.valida(request.email());

        final var documento = request.documento();
        validadorDocumentos.get(documento.tipo()).verificaDocumento(documento.digitos());

        if (request.nome().isEmpty()) {
            throw new CadastroClienteException("Nome cliente vazio!");
        }
    }


}
