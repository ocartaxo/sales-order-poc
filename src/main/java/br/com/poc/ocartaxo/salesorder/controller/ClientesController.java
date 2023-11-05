package br.com.poc.ocartaxo.salesorder.controller;

import br.com.poc.ocartaxo.salesorder.dto.ClienteCadastroRequest;
import br.com.poc.ocartaxo.salesorder.dto.ClienteResponse;
import br.com.poc.ocartaxo.salesorder.service.ClientesService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RequestMapping("/api/cliente")
@RestController
@RequiredArgsConstructor
public class ClientesController {

    private final ClientesService service;

    @PostMapping
    public ResponseEntity<ClienteResponse> cadastrar(
            @RequestBody ClienteCadastroRequest body,
            UriComponentsBuilder builder
    ){
        final var response = service.cadastrarNovoCliente(body);
        final var uri = builder.path("/api/cliente/{id}").buildAndExpand(response.id()).toUri();

        return ResponseEntity.created(uri).body(response);
    }


    @GetMapping("/{id}")
    public ResponseEntity<ClienteResponse> exibir(@PathVariable Long id){
        return ResponseEntity.ok(service.buscarClientePorId(id));
    }

    @GetMapping
    public ResponseEntity<Page<ClienteResponse>> listar(@PageableDefault(size=5)Pageable pageable){
        return ResponseEntity.ok(service.buscarTodosClientes(pageable));
    }
}
