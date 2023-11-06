package br.com.poc.ocartaxo.salesorder.model;

import br.com.poc.ocartaxo.salesorder.enums.TipoDocumento;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Embeddable
@AllArgsConstructor
@NoArgsConstructor
public class Documento {
    private String digitos;
    private TipoDocumento tipoDocumento;
}
