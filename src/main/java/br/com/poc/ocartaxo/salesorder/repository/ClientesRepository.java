package br.com.poc.ocartaxo.salesorder.repository;

import br.com.poc.ocartaxo.salesorder.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientesRepository extends JpaRepository<Cliente, Long> {
}
