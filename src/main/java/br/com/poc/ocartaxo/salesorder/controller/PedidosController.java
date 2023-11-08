package br.com.poc.ocartaxo.salesorder.controller;

import br.com.poc.ocartaxo.salesorder.dto.PedidoAtualizacaoRequest;
import br.com.poc.ocartaxo.salesorder.dto.PedidoCadastroRequest;
import br.com.poc.ocartaxo.salesorder.dto.PedidoDetalhesResponse;
import br.com.poc.ocartaxo.salesorder.dto.PedidoResponse;
import br.com.poc.ocartaxo.salesorder.service.PedidosService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/api/pedido")
@RequiredArgsConstructor
public class PedidosController {

    private final PedidosService service;

    @PostMapping
    public ResponseEntity<PedidoResponse> cadastrar(
            @RequestBody PedidoCadastroRequest body,
            UriComponentsBuilder builder
    ) {

        final var response = service.cadastraNovoPedido(body);
        final var uri = builder.path("/api/pedido/{id}").buildAndExpand(response.id()).toUri();

        return ResponseEntity.created(uri).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PedidoDetalhesResponse> exibir(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @GetMapping
    public ResponseEntity<Page<PedidoResponse>> listar(@PageableDefault(size = 5) Pageable pageable){
        return ResponseEntity.ok(service.listarTodosPedidos(pageable));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<PedidoDetalhesResponse> atualizar(
            @PathVariable Long id,
            @RequestBody PedidoAtualizacaoRequest body)
    {
        return ResponseEntity.ok(service.atualizarPedido(id, body));
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id){
        service.deletarPorId(id);
    }
}
