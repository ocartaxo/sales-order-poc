package br.com.poc.ocartaxo.salesorder.repository;

import br.com.poc.ocartaxo.salesorder.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutosRepository extends JpaRepository<Produto, Long> {
}
