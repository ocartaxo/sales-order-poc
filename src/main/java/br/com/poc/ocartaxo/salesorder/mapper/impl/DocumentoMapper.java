package br.com.poc.ocartaxo.salesorder.mapper.impl;

import br.com.poc.ocartaxo.salesorder.dto.DocumentoRequest;
import br.com.poc.ocartaxo.salesorder.model.Documento;
import org.springframework.stereotype.Component;

@Component
public class DocumentoMapper {

    public Documento converte(DocumentoRequest request){
        final var documento = new Documento();
        documento.setDigitos(request.digitos());
        documento.setTipoDocumento(request.tipo());
        return documento;
    }
}
