package br.com.poc.ocartaxo.salesorder.validacao;

import br.com.poc.ocartaxo.salesorder.infra.exception.DocumentoInvalidoException;

public interface ValidadorDocumento {

    /**
     * Valida se é um documento válido
     * @param digitos Dígitos do documento
     * @throws DocumentoInvalidoException
     */
    void verificaDocumento(String digitos) throws DocumentoInvalidoException;
}
