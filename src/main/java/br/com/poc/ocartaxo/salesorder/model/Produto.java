package br.com.poc.ocartaxo.salesorder.model;

import br.com.poc.ocartaxo.salesorder.dto.ProdutoAtualizacaoRequest;
import br.com.poc.ocartaxo.salesorder.enums.CategoriaProduto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String descricao;

    @Enumerated(EnumType.STRING)
    private CategoriaProduto categoria;

    private BigDecimal valor;

    private int quantidadeEstoque;

    public void atualiza(ProdutoAtualizacaoRequest body) {
        this.valor = body.valor();
        this.quantidadeEstoque = body.quantidadeEstoque();
    }
}
