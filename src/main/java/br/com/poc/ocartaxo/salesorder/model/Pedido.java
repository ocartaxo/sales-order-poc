package br.com.poc.ocartaxo.salesorder.model;

import br.com.poc.ocartaxo.salesorder.enums.StatusPedido;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
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

    @Setter(value = AccessLevel.NONE)
    private BigDecimal valorTotal;

    @ManyToOne
    private Cliente cliente;

    private String enderecoEntrega;

    private String enderecoCobranca;

    @Enumerated(EnumType.STRING)
    private StatusPedido status = StatusPedido.CONFIRMADO;

    @OneToMany(cascade = CascadeType.ALL)
    private List<PedidoProduto> produtos;


    public void calculaValorTotal() {

        this.valorTotal = produtos.stream()
                .map(produtoPedido -> produtoPedido.getProduto().getValor()
                .multiply(new BigDecimal(produtoPedido.getQuantidade())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
