package br.com.poc.ocartaxo.salesorder.repository;

import br.com.poc.ocartaxo.salesorder.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidosRepository extends JpaRepository<Pedido, Long> {

}
