package br.com.poc.ocartaxo.salesorder.validacao.impl;

import br.com.poc.ocartaxo.salesorder.dto.ClienteAtualizacaoRequest;
import br.com.poc.ocartaxo.salesorder.dto.ClienteCadastroRequest;
import br.com.poc.ocartaxo.salesorder.enums.TipoDocumento;
import br.com.poc.ocartaxo.salesorder.infra.exception.InformacoesClienteInvalidaException;
import br.com.poc.ocartaxo.salesorder.validacao.ValidadorCliente;
import br.com.poc.ocartaxo.salesorder.validacao.ValidadorDocumento;
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

    /***
     * Valida as informações de cadastro de um cliente
     * @param request Informações do cadastro de um novo cliente
     * @throws InformacoesClienteInvalidaException
     */
    @Override
    public void validaDadosCadastroCliente(ClienteCadastroRequest request) {
        log.info("Validando o cliente `%s`".formatted(request.nome()));

        validadorEmail.valida(request.email());

        final var documento = request.documento();
        validadorDocumentos.get(documento.tipo()).verificaDocumento(documento.digitos());

        if (request.nome().isBlank()) {
            throw new InformacoesClienteInvalidaException("Nome cliente vazio!");
        }
    }

    @Override
    public void validaDadosAtualizacaoCliente(ClienteAtualizacaoRequest body) {
        log.info("Validando a atualização no cliente `%s`".formatted(body.nome()));
        validadorEmail.valida(body.email());
        if (body.nome().isBlank()) {
            throw new InformacoesClienteInvalidaException("Nome cliente vazio!");
        }
    }
}
