package br.com.poc.ocartaxo.salesorder.model;

import br.com.poc.ocartaxo.salesorder.dto.ClienteAtualizacaoRequest;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@EqualsAndHashCode(of = {"id", "nome", "email", "documento"})
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String email;
    @Embedded
    private Documento documento;
    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    @JoinTable(
            name = "cliente_endereco",
            joinColumns = @JoinColumn(name = "cliente_id"),
            inverseJoinColumns = @JoinColumn(name = "endereco_id")
    )
    private List<Endereco> enderecos;

    @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, mappedBy = "cliente")
    private List<Pedido> pedidos = new ArrayList<>();

    public void atualiza(ClienteAtualizacaoRequest atualizacao){
        this.nome = atualizacao.nome();
        this.email = atualizacao.email();
    }
}
