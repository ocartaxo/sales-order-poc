package br.com.poc.ocartaxo.salesorder.service;

import br.com.poc.ocartaxo.salesorder.dto.PedidoAtualizacaoRequest;
import br.com.poc.ocartaxo.salesorder.dto.PedidoCadastroRequest;
import br.com.poc.ocartaxo.salesorder.dto.PedidoDetalhesResponse;
import br.com.poc.ocartaxo.salesorder.dto.PedidoResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PedidosService {
    PedidoResponse cadastraNovoPedido(PedidoCadastroRequest body);

    PedidoDetalhesResponse buscarPorId(Long id);

    Page<PedidoResponse> listarTodosPedidos(Pageable pageable);

    PedidoDetalhesResponse atualizarPedido(Long id, PedidoAtualizacaoRequest body);

    void deletarPorId(Long id);


}
