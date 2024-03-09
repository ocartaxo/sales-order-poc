package br.com.poc.ocartaxo.salesorder.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;


@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class ItemPedido {
    @EmbeddedId
    @JsonIgnore
    private ItemPedidoPk itemPedidoPk;

    private int quantidade;

    @Transient
    public Produto getProduto() {
        return itemPedidoPk.getProduto();
    }

    @Transient
    public BigDecimal getValorTotalProduto() {
        return itemPedidoPk.getProduto().getValor().multiply(BigDecimal.valueOf(quantidade));
    }
}
