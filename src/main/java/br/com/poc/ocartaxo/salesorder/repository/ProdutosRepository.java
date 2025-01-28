package br.com.poc.ocartaxo.salesorder.repository;

import br.com.poc.ocartaxo.salesorder.model.Produto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProdutosRepository extends JpaRepository<Produto, Long> {
    @Query("SELECT p FROM Produto p WHERE p.quantidadeEstoque > 0")
    Page<Produto> buscaTodosProdutosEmEstoque(Pageable p);
}
