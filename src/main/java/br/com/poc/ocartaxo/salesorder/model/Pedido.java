package br.com.poc.ocartaxo.salesorder.model;

import br.com.poc.ocartaxo.salesorder.enums.StatusPedido;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime data = LocalDateTime.now();

    @ManyToOne
    private Cliente cliente;

    private String enderecoEntrega;

    private String enderecoCobranca;

    @Enumerated(EnumType.STRING)
    private StatusPedido status = StatusPedido.CONFIRMADO;

    @JsonManagedReference
    @OneToMany(mappedBy = "itemPedidoPk.pedido", cascade = CascadeType.ALL)
    private List<ItemPedido> produtos = new ArrayList<>();

    @Transient
    private BigDecimal totalValorPedido;


    public BigDecimal getValorTotalPedido() {
        return produtos.stream().map(ItemPedido::getValorTotalProduto).reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    @Transient
    public int getTotalProdutos() {
        return produtos.size();
    }

}
